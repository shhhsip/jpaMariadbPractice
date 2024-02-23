package pratice.mariaAndQuerydsl.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
public class PayId implements Serializable {

    @Column(insertable=false, updatable=false)
    private Long employee_id;
    private LocalDate start_date;


}
