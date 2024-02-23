package pratice.mariaAndQuerydsl.repository;

import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import pratice.mariaAndQuerydsl.dto.EmployeeCondition;
import pratice.mariaAndQuerydsl.dto.EmployeeDepartmentPayDto;
import pratice.mariaAndQuerydsl.entity.SexType;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class EmployeeRepositoryTest {


    @Autowired
    EntityManager em;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void search() {
        //given
        List<EmployeeDepartmentPayDto> search = employeeRepository.search(new EmployeeCondition());

        for (EmployeeDepartmentPayDto employeeDepartmentPayDto : search) {
            System.out.println("employeeDepartmentPayDto = " + employeeDepartmentPayDto);
        }
    }

    @Test
    public void searchPageNameLike() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        EmployeeCondition condition = new EmployeeCondition();
        condition.setName("Szabolcs");
        Page<EmployeeDepartmentPayDto> search = employeeRepository.searchPage(condition, pageRequest);

        for (EmployeeDepartmentPayDto employeeDepartmentPayDto : search) {
            assertThat(employeeDepartmentPayDto.getLast_name()).isEqualTo("Szabolcs");
        }
    }

    @Test
    public void searchPageDepartmentLike() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        EmployeeCondition condition = new EmployeeCondition();
        condition.setDepartment_name("Development");

        Page<EmployeeDepartmentPayDto> search = employeeRepository.searchPage(condition, pageRequest);

        for (EmployeeDepartmentPayDto employeeDepartmentPayDto : search) {
            assertThat(employeeDepartmentPayDto.getDepartment_name()).isEqualTo("Development");
        }
    }

    @Test
    public void searchPageSexEq() throws Exception{
        PageRequest pageRequest = PageRequest.of(0, 10);
        EmployeeCondition condition = new EmployeeCondition();
        condition.setSex(SexType.M);

        Page<EmployeeDepartmentPayDto> search = employeeRepository.searchPage(condition, pageRequest);

        for (EmployeeDepartmentPayDto employeeDepartmentPayDto : search) {
            assertThat(employeeDepartmentPayDto.getSex()).isEqualTo(SexType.M);
        }
    }

    @Test
    public void searchPageSalaryLoeGoe() throws Exception{
        PageRequest pageRequest = PageRequest.of(0, 10);
        EmployeeCondition condition = new EmployeeCondition();
        condition.setSalaryGoe(100000);
        condition.setSalaryLoe(110000);

        Page<EmployeeDepartmentPayDto> search = employeeRepository.searchPage(condition, pageRequest);

        for (EmployeeDepartmentPayDto employeeDepartmentPayDto : search) {
            //System.out.println("employeeDepartmentPayDto = " + employeeDepartmentPayDto);
        }
    }

    @Test
    public void searchPageAllSearch() throws Exception{
        PageRequest pageRequest = PageRequest.of(0, 10);
        EmployeeCondition condition = new EmployeeCondition();
        condition.setName("Schusler");
        condition.setSex(SexType.M);
        condition.setDepartment_name("Development");
        condition.setSalaryGoe(100000);
        condition.setSalaryLoe(110000);

        Page<EmployeeDepartmentPayDto> search = employeeRepository.searchPage(condition, pageRequest);

        //assertThat(search.getContent().get(0).getEmployee_id()).isEqualTo(10066L);
        for (EmployeeDepartmentPayDto result : search) {
            assertThat(result.getEmployee_id()).isEqualTo(10066L);
        }
    }

    @Test
    public void searchPageSearchParamCount() throws Exception{
        PageRequest pageRequest = PageRequest.of(0, 1000);
        EmployeeCondition condition = new EmployeeCondition();
        condition.setSex(SexType.M);
        condition.setParamCount(1000L);


        Page<EmployeeDepartmentPayDto> search = employeeRepository.searchPage(condition, pageRequest);

        System.out.println("search = " + search.getSize());

        assertThat(search).size().isEqualTo(1000);
    }


}