package pratice.mariaAndQuerydsl.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * 사원 테이블
 */
@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class Employee {


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long id; // 사원번호

    private LocalDate birth_date; // 생년월일
    private String last_name; // 이름

    private String first_name; // 성

    @Enumerated(EnumType.STRING)
    private SexType sex; // 성별

    private LocalDate employee_date; // 입사일자

/*
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;*/

    @OneToMany(mappedBy = "employee")
    @JsonIgnore
    private List<Pay> pays = new ArrayList<>();

    @OneToMany(mappedBy = "employee")
    @JsonIgnore
    private List<EmployeeDepartment> departments = new ArrayList<>();

   /* @OneToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "employee_id", referencedColumnName = "employee_id"),
            @JoinColumn(name = "department_id", referencedColumnName = "department_id")
    })
    private EmployeeDepartment department;*/

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", birth_date=" + birth_date +
                ", last_name='" + last_name + '\'' +
                ", first_name='" + first_name + '\'' +
                ", sex=" + sex +
                ", employee_date=" + employee_date +
                '}';
    }
}
