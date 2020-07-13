package pl.dmuszynski.aquashop.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;

    private final String email;

    private final String password;

    @OneToMany
    private final List<Address> addresses;

    @ManyToMany
    private final Set<Role> roles;

    @OneToOne
    private final Person person;

    @CreatedDate
    private final LocalDate created;

    private final boolean isEnabled;

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
        private final String email;
        private final String password;
        private List<Address> addresses;
        private Person person;
        private Set<Role> roles;
        private LocalDate created;
        private boolean isEnabled;

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

        public UserBuilder person(Person person) {
            this.person = person;
            return this;
        }

        public UserBuilder roles(Set<Role> roles) {
            this.roles = roles;
            return this;
        }

        public UserBuilder created(LocalDate created) {
            this.created = created;
            return this;
        }

        public UserBuilder isEnabled(boolean isEnabled) {
            this.isEnabled = isEnabled;
            return this;
        }

        public User build() {
            User user =  new User(this);
            validateUserObject(user);
            return user;
        }

        private void validateUserObject(User user) {
            if(user.roles.isEmpty())
                throw new IllegalStateException("roles cannot be empty");
        }
    }
}
