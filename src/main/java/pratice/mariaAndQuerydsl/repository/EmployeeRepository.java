package pratice.mariaAndQuerydsl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pratice.mariaAndQuerydsl.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>, EmployeeRepositoryCustom {
}
