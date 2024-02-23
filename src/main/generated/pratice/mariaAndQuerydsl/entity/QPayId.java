package pratice.mariaAndQuerydsl.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPayId is a Querydsl query type for PayId
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QPayId extends BeanPath<PayId> {

    private static final long serialVersionUID = 2129623960L;

    public static final QPayId payId = new QPayId("payId");

    public final NumberPath<Long> employee_id = createNumber("employee_id", Long.class);

    public final DatePath<java.time.LocalDate> start_date = createDate("start_date", java.time.LocalDate.class);

    public QPayId(String variable) {
        super(PayId.class, forVariable(variable));
    }

    public QPayId(Path<? extends PayId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPayId(PathMetadata metadata) {
        super(PayId.class, metadata);
    }

}

