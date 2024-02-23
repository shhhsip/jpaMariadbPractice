package pratice.mariaAndQuerydsl.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * 급여 테이블
 */
@Entity
@Getter
@Setter
public class Pay {
    /*@Id
    @GeneratedValue
    @Column(name = "employee_id")
    private Long id; // 급여번호*/

    @EmbeddedId
    private PayId payId;

    private Long salary; // 연봉

    //private LocalDate start_date;

    private LocalDate end_date;

    private char use_yn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;




}
