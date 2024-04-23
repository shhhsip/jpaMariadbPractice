package pratice.mariaAndQuerydsl.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import pratice.mariaAndQuerydsl.entity.SexType;

import java.time.LocalDate;

@Data
public class PayDto {
    private Long employee_id;
    private Long salary;

    @QueryProjection
    public PayDto(Long employee_id, Long salary) {
        this.employee_id = employee_id;
        this.salary = salary;
    }
}
