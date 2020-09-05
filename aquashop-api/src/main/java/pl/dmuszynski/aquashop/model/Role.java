package pl.dmuszynski.aquashop.model;

import javax.validation.constraints.NotNull;
import javax.persistence.*;

import lombok.NoArgsConstructor;
import lombok.Data;

import java.util.List;

@Entity
@Data @NoArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "role_type"))
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NotNull @Column(name = "role_type")
    private RoleType roleType;

    @ManyToMany(mappedBy = "roles", cascade = CascadeType.ALL)
    private List<User> users;

    public Role(RoleType roleType) {
        this.roleType = roleType;
    }
}