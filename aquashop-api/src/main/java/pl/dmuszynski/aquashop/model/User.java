package pl.dmuszynski.aquashop.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.annotation.CreatedDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import lombok.Builder;
import lombok.Getter;

@Entity
@Data @NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"username", "email"}))
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @NotBlank
    private String email;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Address> addresses;

    @OneToOne(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Token token;

    @OneToOne(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Person person;

    @NotNull
    private boolean isEnabled;

    @CreatedDate
    @NotNull @Column(updatable = false)
    private LocalDateTime creationDate;

    public User(String username, String password, String email, Set<Role> roles) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = roles;
    }
}
