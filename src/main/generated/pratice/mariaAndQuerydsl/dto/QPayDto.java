package pratice.mariaAndQuerydsl.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * pratice.mariaAndQuerydsl.dto.QPayDto is a Querydsl Projection type for PayDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QPayDto extends ConstructorExpression<PayDto> {

    private static final long serialVersionUID = 1076840646L;

    public QPayDto(com.querydsl.core.types.Expression<Long> employee_id, com.querydsl.core.types.Expression<Long> salary) {
        super(PayDto.class, new Class<?>[]{long.class, long.class}, employee_id, salary);
    }

}

