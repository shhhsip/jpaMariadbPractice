package pratice.mariaAndQuerydsl.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEmployee is a Querydsl query type for Employee
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEmployee extends EntityPathBase<Employee> {

    private static final long serialVersionUID = -1785330983L;

    public static final QEmployee employee = new QEmployee("employee");

    public final DatePath<java.time.LocalDate> birth_date = createDate("birth_date", java.time.LocalDate.class);

    public final ListPath<EmployeeDepartment, QEmployeeDepartment> departments = this.<EmployeeDepartment, QEmployeeDepartment>createList("departments", EmployeeDepartment.class, QEmployeeDepartment.class, PathInits.DIRECT2);

    public final DatePath<java.time.LocalDate> employee_date = createDate("employee_date", java.time.LocalDate.class);

    public final StringPath first_name = createString("first_name");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath last_name = createString("last_name");

    public final ListPath<Pay, QPay> pays = this.<Pay, QPay>createList("pays", Pay.class, QPay.class, PathInits.DIRECT2);

    public final EnumPath<SexType> sex = createEnum("sex", SexType.class);

    public QEmployee(String variable) {
        super(Employee.class, forVariable(variable));
    }

    public QEmployee(Path<? extends Employee> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEmployee(PathMetadata metadata) {
        super(Employee.class, metadata);
    }

}

