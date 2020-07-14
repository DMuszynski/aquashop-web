package pl.dmuszynski.aquashop.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.annotation.CreatedDate;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", unique = true, updatable = false, nullable = false)
    private Long id;

//    @Column(unique = true)
    private String email;

//    @Column(unique = true)
    private String password;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Address> addresses;

    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<Role>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id")
    private Person person;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime created;

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
            System.out.println("JEST " + user.getRoles().size());
            if(user.roles.isEmpty())
                throw new IllegalStateException("roles cannot be empty");
        }
    }
}
