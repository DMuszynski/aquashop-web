package pl.dmuszynski.aquashop.model;

import javax.validation.constraints.NotNull;
import javax.persistence.*;

import lombok.NoArgsConstructor;
import lombok.Data;

@Entity
@Data @NoArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "role_type"))
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @NotNull @Column(name = "role_type")
    private RoleType roleType;

    public Role(RoleType roleType) {
        this.roleType = roleType;
    }
}