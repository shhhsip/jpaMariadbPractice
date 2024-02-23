package pratice.mariaAndQuerydsl.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QEmployeeDepartmentId is a Querydsl query type for EmployeeDepartmentId
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QEmployeeDepartmentId extends BeanPath<EmployeeDepartmentId> {

    private static final long serialVersionUID = -376858234L;

    public static final QEmployeeDepartmentId employeeDepartmentId = new QEmployeeDepartmentId("employeeDepartmentId");

    public final StringPath department_id = createString("department_id");

    public final NumberPath<Long> employee_id = createNumber("employee_id", Long.class);

    public QEmployeeDepartmentId(String variable) {
        super(EmployeeDepartmentId.class, forVariable(variable));
    }

    public QEmployeeDepartmentId(Path<? extends EmployeeDepartmentId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEmployeeDepartmentId(PathMetadata metadata) {
        super(EmployeeDepartmentId.class, metadata);
    }

}

