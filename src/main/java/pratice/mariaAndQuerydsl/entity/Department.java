package pratice.mariaAndQuerydsl.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 부서 테이블
 */
@Entity
@Getter
@Setter
public class Department {

    @Id
    private String department_id; // 부서번호

    private String name; // 부서명

    private String etc; // 비고


    @OneToOne(mappedBy = "department")
    private EmployeeDepartment employeeDepartment;

    /*@OneToMany(mappedBy = "department")
    @JsonIgnore
    private List<EmployeeDepartment> departmentList = new ArrayList<>();*/

}
