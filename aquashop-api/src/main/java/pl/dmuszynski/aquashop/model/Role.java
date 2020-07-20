package pl.dmuszynski.aquashop.model;

import javax.persistence.*;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", unique = true, nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private RoleType roleType;

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