package pl.dmuszynski.aquashop.model;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private String name;

    private String surname;

    private String phoneNumber;

    private LocalDate birth;

    @CreatedDate
    private LocalDate created;


    @OneToMany
    private List<Address> addresses;

    @ManyToMany
    private Set<Role> roles;

    private boolean isEnabled;

    public static final class UserBuilder {
        private Long id;
        private String email;
        private String password;
        private String name;
        private String surname;
        private String phoneNumber;
        private Set<Role> roles;
        private LocalDateTime created;
        private String activationCode;
        private boolean active = false;
        private boolean logged = false;
        private boolean firstLogin = true;
        private int premiumCurrency = 0;

        public UserBuilder id(Long id) {
            this.id = id;
            return this;
        }


        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder roles(Set<Role> roles) {
            this.roles = roles;
            return this;
        }

        public UserBuilder created(LocalDateTime created) {
            this.created = created;
            return this;
        }

        public UserBuilder active(boolean active) {
            this.active = active;
            return this;
        }

        public UserBuilder logged(boolean logged) {
            this.logged = logged;
            return this;
        }

        public UserBuilder firstLogin(boolean firstLogin) {
            this.firstLogin = firstLogin;
            return this;
        }

        public UserBuilder premiumCurrency(int premiumCurrency) {
            this.premiumCurrency = premiumCurrency;
            return this;
        }

        public UserBuilder activationCode(String activationCode) {
            this.activationCode = activationCode;
            return this;
        }

        public User build() {
            if(roles.isEmpty())
                throw new IllegalStateException("roles cannot be empty");

            User user = new User();
            user.id = this.id;
            user.email = this.email;
            user.password = this.password;
            user.roles = this.roles;

            return user;
        }
    }
}
