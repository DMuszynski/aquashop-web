package pl.dmuszynski.aquashop.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "role_type"))
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @NotNull @Column(name = "role_type")
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