package pratice.mariaAndQuerydsl.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import pratice.mariaAndQuerydsl.entity.SexType;

import java.time.LocalDate;

@Data
public class EmployeeDepartmentPayDto {
    private Long employee_id;
    private String department_name;
    private SexType sex;
    private Long salary;
    private LocalDate birth_date;
    private String first_name;
    private String last_name;

    @QueryProjection
    public EmployeeDepartmentPayDto(Long employee_id, String department_name, SexType sex, Long salary, LocalDate birth_date, String first_name, String last_name) {
        this.employee_id = employee_id;
        this.department_name = department_name;
        this.sex = sex;
        this.salary = salary;
        this.birth_date = birth_date;
        this.first_name = first_name;
        this.last_name = last_name;
    }

    @QueryProjection
    public EmployeeDepartmentPayDto(Long employee_id, LocalDate birth_date, String first_name, String last_name) {
        this.employee_id = employee_id;
        this.birth_date = birth_date;
        this.first_name = first_name;
        this.last_name = last_name;
    }

    @QueryProjection
    public EmployeeDepartmentPayDto(Long employee_id, String department_name, Long salary, LocalDate birth_date, String first_name, String last_name) {
        this.employee_id = employee_id;
        this.department_name = department_name;
        this.salary = salary;
        this.birth_date = birth_date;
        this.first_name = first_name;
        this.last_name = last_name;
    }

    @QueryProjection
    public EmployeeDepartmentPayDto(Long employee_id, Long salary, LocalDate birth_date, String first_name, String last_name) {
        this(employee_id, null, salary, birth_date, first_name, last_name);
    }

    @QueryProjection
    public EmployeeDepartmentPayDto(Long employee_id, String department_name, LocalDate birth_date, String first_name, String last_name) {
        this(employee_id, department_name, null, birth_date, first_name, last_name);
    }
}
