package pratice.mariaAndQuerydsl.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.io.Serializable;

@Embeddable
public class EmployeeDepartmentId implements Serializable {
    @Column(insertable=false, updatable=false)
    private String department_id;
    @Column(insertable=false, updatable=false)
    private Long employee_id;
}
