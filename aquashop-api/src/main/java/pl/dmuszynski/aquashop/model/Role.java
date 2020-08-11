package pl.dmuszynski.aquashop.model;

import javax.validation.constraints.NotNull;
import javax.persistence.*;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "role_type"))
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;

    @NotNull @Enumerated(EnumType.STRING)
    private final RoleType roleType;

    public Role(RoleType roleType) {
        this.roleType = roleType;
    }

    public Role() {
        this(RoleType.ROLE_USER);
    }

    public Long getId() {
        return id;
    }

    public RoleType getRoleType() {
        return roleType;
    }
}