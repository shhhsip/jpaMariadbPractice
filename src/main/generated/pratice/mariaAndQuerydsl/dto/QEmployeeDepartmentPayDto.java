package pratice.mariaAndQuerydsl.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

import com.querydsl.core.types.Expression;

/**
 * pratice.mariaAndQuerydsl.dto.QEmployeeDepartmentPayDto is a Querydsl Projection type for EmployeeDepartmentPayDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QEmployeeDepartmentPayDto extends ConstructorExpression<EmployeeDepartmentPayDto> {

    private static final long serialVersionUID = 358683590L;

    public QEmployeeDepartmentPayDto(Expression<Long> employee_id, Expression<String> department_name, Expression<pratice.mariaAndQuerydsl.entity.SexType> sex, Expression<Long> salary, Expression<java.time.LocalDate> birth_date, Expression<String> first_name, Expression<String> last_name) {
        super(EmployeeDepartmentPayDto.class, new Class<?>[]{long.class, String.class, pratice.mariaAndQuerydsl.entity.SexType.class, long.class, java.time.LocalDate.class, String.class, String.class}, employee_id, department_name, sex, salary, birth_date, first_name, last_name);
    }

    public QEmployeeDepartmentPayDto(Expression<Long> employee_id, Expression<java.time.LocalDate> birth_date, Expression<String> first_name, Expression<String> last_name) {
        super(EmployeeDepartmentPayDto.class, new Class<?>[]{long.class, java.time.LocalDate.class, String.class, String.class}, employee_id, birth_date, first_name, last_name);
    }

    public QEmployeeDepartmentPayDto(Expression<Long> employee_id, Expression<String> department_name, Expression<Long> salary, Expression<java.time.LocalDate> birth_date, Expression<String> first_name, Expression<String> last_name) {
        super(EmployeeDepartmentPayDto.class, new Class<?>[]{long.class, String.class, long.class, java.time.LocalDate.class, String.class, String.class}, employee_id, department_name, salary, birth_date, first_name, last_name);
    }

    public QEmployeeDepartmentPayDto(Expression<Long> employee_id, Expression<Long> salary, Expression<java.time.LocalDate> birth_date, Expression<String> first_name, Expression<String> last_name) {
        super(EmployeeDepartmentPayDto.class, new Class<?>[]{long.class, long.class, java.time.LocalDate.class, String.class, String.class}, employee_id, salary, birth_date, first_name, last_name);
    }

    public QEmployeeDepartmentPayDto(NumberExpression<Long> employee_id, StringExpression department_name, TemporalExpression<java.time.LocalDate> birth_date, StringExpression first_name, StringExpression last_name) {
        super(EmployeeDepartmentPayDto.class, new Class<?>[]{long.class, String.class, java.time.LocalDate.class, String.class, String.class}, employee_id, department_name, birth_date, first_name, last_name);
    }

}

