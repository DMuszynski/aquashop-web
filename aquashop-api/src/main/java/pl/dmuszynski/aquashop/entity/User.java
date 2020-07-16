package pl.dmuszynski.aquashop.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", unique = true, updatable = false, nullable = false)
    private Long id;
    
    @Column(length = 35, unique = true, nullable = false)
    private String email;

    @Column(length = 30, unique = true, nullable = false)
    private String password;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Address> addresses;

    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id")
    private Person person;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime created;

    @Column
    private boolean isEnabled;

    protected User() { }

    private User(UserBuilder userBuilder) {
        this.id = userBuilder.id;
        this.email = userBuilder.email;
        this.password = userBuilder.password;
        this.addresses = userBuilder.addresses;
        this.roles = userBuilder.roles;
        this.person = userBuilder.person;
        this.created = userBuilder.created;
        this.isEnabled = userBuilder.isEnabled;
    }

    public static final class UserBuilder {
        private Long id;
        private String email;
        private String password;
        private List<Address> addresses;
        private Set<Role> roles;
        private Person person;
        private boolean isEnabled;
        private LocalDateTime created;

        public UserBuilder(String email, String password) {
            this.email = email;
            this.password = password;
        }

        public UserBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public UserBuilder addresses(List<Address> addresses) {
            this.addresses = addresses;
            return this;
        }

        public UserBuilder roles(Set<Role> roles) {
            this.roles = roles;
            return this;
        }

        public UserBuilder person(Person person) {
            this.person = person;
            return this;
        }

        public UserBuilder isEnabled(boolean isEnabled) {
            this.isEnabled = isEnabled;
            return this;
        }

        public UserBuilder created(LocalDateTime created) {
            this.created = created;
            return this;
        }

        public User build() {
            User user = new User(this);
            validateUserObject(user);
            return user;
        }

        private void validateUserObject(final User user) {
            if (user.roles.isEmpty())
                throw new IllegalStateException("roles cannot be empty");
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

    public List<Address> getAddresses() {
        return addresses;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public Person getPerson() {
        return person;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public boolean isEnabled() {
        return isEnabled;
    }
}
