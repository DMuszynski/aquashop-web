package pl.dmuszynski.aquashop.model;

import javax.validation.constraints.NotNull;
import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "role_type"))
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @NotNull @Column(name = "role_type")
    private RoleType roleType = RoleType.ROLE_USER;

    public Role(RoleType roleType) {
        this.roleType = roleType;
    }
}