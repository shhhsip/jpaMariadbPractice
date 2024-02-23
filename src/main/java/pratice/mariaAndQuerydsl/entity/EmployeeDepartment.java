package pratice.mariaAndQuerydsl.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class EmployeeDepartment {

    /*@Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "Employee_department_id")
    private Long id;*/
    @EmbeddedId
    private EmployeeDepartmentId employeeDepartmentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id")
    private Employee employee;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;


    private LocalDate start_date;
    private LocalDate end_date;

}
