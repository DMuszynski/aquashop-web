package pl.dmuszynski.aquashop.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.annotation.CreatedDate;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"username", "email", "password"}))
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

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Address> addresses;

    @JsonIgnore
    @OneToOne(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Token token;

    @JsonIgnore
    @OneToOne(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Person person;

    @NotNull
    private boolean isEnabled;

    @CreatedDate
    @NotNull @Column(updatable = false)
    private LocalDateTime creationDate;

    protected User() {}

    private User(UserBuilder userBuilder) {
        this.id = userBuilder.id;
        this.username = userBuilder.username;
        this.email = userBuilder.email;
        this.password = userBuilder.password;
        this.roles = userBuilder.roles;
        this.addresses = userBuilder.addresses;
        this.isEnabled = userBuilder.isEnabled;
        this.creationDate = userBuilder.creationDate;
    }

    public static final class UserBuilder {
        private Long id;
        private final String username;
        private final String email;
        private final String password;
        private Set<Role> roles;
        private List<Address> addresses;
        private boolean isEnabled;
        private LocalDateTime creationDate;

        public UserBuilder(String username, String email, String password) {
            this.username = username;
            this.email = email;
            this.password = password;
        }

        public UserBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public UserBuilder roles(Set<Role> roles) {
            this.roles = roles;
            return this;
        }

        public UserBuilder addresses(List<Address> addresses) {
            this.addresses = addresses;
            return this;
        }

        public UserBuilder isEnabled(boolean isEnabled) {
            this.isEnabled = isEnabled;
            return this;
        }

        public UserBuilder creationDate(LocalDateTime creationDate) {
            this.creationDate = creationDate;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public Token getToken() {
        return token;
    }

    public String getUsername() {
        return username;
    }
}
