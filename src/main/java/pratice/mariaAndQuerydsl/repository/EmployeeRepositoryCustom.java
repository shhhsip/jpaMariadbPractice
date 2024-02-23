package pratice.mariaAndQuerydsl.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pratice.mariaAndQuerydsl.dto.EmployeeCondition;
import pratice.mariaAndQuerydsl.dto.EmployeeDepartmentPayDto;

import java.util.List;


public interface EmployeeRepositoryCustom {

    List<EmployeeDepartmentPayDto> search(EmployeeCondition condition);
    Page<EmployeeDepartmentPayDto> searchPage(EmployeeCondition condition, Pageable pageable);
}
