package pratice.mariaAndQuerydsl.dto;

import lombok.Data;
import pratice.mariaAndQuerydsl.entity.SexType;

@Data
public class EmployeeCondition {
    private String name;
    private String department_name;
    private SexType sex;
    private Integer salaryGoe;
    private Integer salaryLoe;
}
