package pratice.mariaAndQuerydsl.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.util.StringUtils;
import pratice.mariaAndQuerydsl.dto.*;
import pratice.mariaAndQuerydsl.entity.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static com.querydsl.core.types.ExpressionUtils.count;
import static pratice.mariaAndQuerydsl.entity.QDepartment.*;
import static pratice.mariaAndQuerydsl.entity.QEmployee.*;
import static pratice.mariaAndQuerydsl.entity.QEmployeeDepartment.*;
import static pratice.mariaAndQuerydsl.entity.QPay.*;

public class EmployeeRepositoryImpl implements EmployeeRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public EmployeeRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<EmployeeDepartmentPayDto> search(EmployeeCondition condition) {
        List<EmployeeDepartmentPayDto> result = queryFactory
                .select(new QEmployeeDepartmentPayDto(
                        employee.id.as("employee_id"),
                        department.name.as("department_name"),
                        pay.salary,
                        employee.birth_date,
                        employee.first_name,
                        employee.last_name
                ))
                .from(employee)
                .leftJoin(department).on(
                        employee.id.eq(employeeDepartment.employee.id),
                        employeeDepartment.end_date.eq(LocalDate.of(9999,1,1))
                )
                .leftJoin(employee.pays, pay).on(
                        pay.use_yn.eq('1')
                )
                .groupBy(employee.id)
                /*.offset(0)
                .limit(20)*/
                .fetch();

        return result;
    }

    /**
     * 단순 select ... limit 0,340000 속도 개선 (마지막 페이지 속도 개선 )
     * 1. 29초 -> 드리븐 테이블 중 pay 테이블의 데이터수가 많음
     * 2. pay 테이블 i_employee_id_use_yn(employee_id, use_yn ) 인덱스 추가 -> 4.5초
     *
     * 페이징을 위한 카운트 쿼리 개선 방향은?
     *  1. 검색 버튼 사용시 페이지 건수 고정하기 ( 구글 )
     *  2. 첫 페이지 조회 결과 캐싱 하기
     *  3. UI 자체를 변경(noOffset, 커서기반페이징)
     *  4. 테이블 설정 변경 및 추가 -> 현재 재직 구분값인 employeeDepartment테이블의 end_date와
     *  현재 연봉 관련 pay테이블의 use_yn = 1 값을 가진 테이블 생성 후 관리
     */

    @Override
    public Page<EmployeeDepartmentPayDto> searchPage(EmployeeCondition condition, Pageable pageable) {

        // 1800~2400ms
        List<EmployeeDepartmentPayDto> content = queryFactory
                .select(new QEmployeeDepartmentPayDto(
                        employee.id.as("employee_id"),
                        department.name.as("department_name"),
                        employee.sex,
                        pay.salary,
                        employee.birth_date,
                        employee.first_name,
                        employee.last_name
                ))
                .from(employee)
                .join(department).on(
                        employee.id.eq(employeeDepartment.employee.id),
                        employeeDepartment.end_date.eq(LocalDate.of(9999, 1, 1))
                )
                .join(employee.pays, pay).on(
                        pay.use_yn.eq('1')
                )
                .groupBy(employee.id)
                .where(
                        allSearch(condition)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        /**
         * 첫 페이지 이후에 파라미터 값으로 카운트 쿼리 대체
         * 1000ms
         */
        JPAQuery<Long> countQuery = null;
        if(condition.getParamCount() > 0){
            countQuery = queryFactory
                    .select(Expressions.constant(condition.getParamCount()))
                    .from(employee)
                    .limit(1);
        }else {
            countQuery = queryFactory
                    .select(
                            employee.count()
                    )
                    .from(employee)
                    .join(department).on(
                            employee.id.eq(employeeDepartment.employee.id),
                            employeeDepartment.end_date.eq(LocalDate.of(9999, 1, 1))
                    )
                    .join(employee.pays, pay).on(
                            pay.use_yn.eq('1')
                    )
                    .where(
                            allSearch(condition)
                    );
        }

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }


    /**
     * employee_id 값 조회후 in절로 pay테이블 조회 후 salary 데이터 set
     * searchPage보다 최대 1.5초 감소 -> 1.9초
     */
    public Page<EmployeeDepartmentPayDto> searchPage2(EmployeeCondition condition, Pageable pageable) {

        // 0ms~1ms
        List<EmployeeDepartmentPayDto> content = queryFactory
                .select(new QEmployeeDepartmentPayDto(
                        employee.id.as("employee_id"),
                        department.name.as("department_name"),
                        /*employee.sex,*/
                        employee.birth_date,
                        employee.first_name,
                        employee.last_name
                ))
                .from(employee)
                .join(department).on(
                        employee.id.eq(employeeDepartment.employee.id),
                        employeeDepartment.end_date.eq(LocalDate.of(9999, 1, 1))
                )
                .groupBy(employee.id)
                .where(
                        allSearch(condition)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        /**
         * pay테이블에서 따로 조회 900ms
         */
        List<Long> collect = content.stream()
                .map(EmployeeDepartmentPayDto::getEmployee_id)
                .collect(Collectors.toList());

        List<PayDto> fetch = queryFactory
                .select(new QPayDto(
                        pay.employee.id,
                        pay.salary
                ))
                .from(pay)
                .where(
                        pay.use_yn.eq('1')
                                .and(pay.employee.id.in(collect))
                ).fetch();

        Map<Long, Long> isToSalary =
                fetch.stream().collect(Collectors.toMap(PayDto::getEmployee_id, PayDto::getSalary));

        content.forEach(dto -> dto.setSalary(
                Optional.ofNullable(isToSalary.get(dto.getEmployee_id())).orElse(dto.getSalary())
        ));



        /**
         * 첫 페이지 이후에 파라미터 값으로 카운트 쿼리 대체
         * 1000ms
         */
        JPAQuery<Long> countQuery = null;
        if(condition.getParamCount() > 0){
            countQuery = queryFactory
                    .select(Expressions.constant(condition.getParamCount()))
                    .from(employee)
                    .limit(1);
        }else {
            countQuery = queryFactory
                    .select(
                            employee.count()
                    )
                    .from(employee)
                    .join(department).on(
                            employee.id.eq(employeeDepartment.employee.id),
                            employeeDepartment.end_date.eq(LocalDate.of(9999, 1, 1))
                    )
                    .join(employee.pays, pay).on(
                            pay.use_yn.eq('1')
                    )
                    .where(
                            allSearch(condition)
                    );
        }

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }





    private static BooleanBuilder allSearch(EmployeeCondition condition) {
        return usernameLike(condition).and(departmentLike(condition)).and(sexEq(condition))
                .and(salaryLoe(condition)).and(salaryGoe(condition));
    }

    private static BooleanBuilder usernameLike(EmployeeCondition condition) {
        return StringUtils.hasText(condition.getName()) ?
                new BooleanBuilder(employee.first_name.contains(condition.getName()).or(employee.last_name.contains(condition.getName())))
                : new BooleanBuilder();
    }


    private static BooleanBuilder salaryGoe(EmployeeCondition condition) {
        return condition.getSalaryGoe() != null ?
                new BooleanBuilder(pay.salary.goe(condition.getSalaryGoe())) : new BooleanBuilder();
        // nullSafeBuilder(() -> pay.salary.goe(condition.getSalaryGoe()));
        //condition.getSalaryGoe() != null ? pay.salary.goe(condition.getSalaryGoe()) : null;
    }

    private static BooleanBuilder salaryLoe(EmployeeCondition condition) {
        return condition.getSalaryLoe() != null ?
                new BooleanBuilder(pay.salary.loe(condition.getSalaryLoe())) : new BooleanBuilder();

        //nullSafeBuilder(() -> pay.salary.loe(condition.getSalaryLoe()));
        //condition.getSalaryLoe() != null ? pay.salary.loe(condition.getSalaryLoe()) : null;
    }

    private static BooleanBuilder sexEq(EmployeeCondition condition) {
        return condition.getSex() != null ?
                new BooleanBuilder(employee.sex.eq(SexType.valueOf(condition.getSex().name()))) : new BooleanBuilder();
        //nullSafeBuilder(() -> employee.sex.eq(SexType.valueOf(condition.getSex().name())));
        //StringUtils.hasText(condition.getSex().name()) ? employee.sex.eq(SexType.valueOf(condition.getSex().name())) : null;

    }

    private static BooleanBuilder departmentLike(EmployeeCondition condition) {
        return StringUtils.hasText(condition.getDepartment_name()) ?
                new BooleanBuilder(department.name.contains(condition.getDepartment_name())) : new BooleanBuilder();
        //nullSafeBuilder(() -> department.name.contains(condition.getDepartment_name()));
        //StringUtils.hasText(condition.getDepartment_name()) ? department.name.contains(condition.getDepartment_name()) : null;

    }

    public static BooleanBuilder nullSafeBuilder(Supplier<BooleanExpression> f) {
        try {
            return new BooleanBuilder(f.get());
        } catch (IllegalArgumentException e) {
            return new BooleanBuilder();
        }
    }


}
