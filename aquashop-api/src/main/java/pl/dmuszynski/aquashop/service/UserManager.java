package pl.dmuszynski.aquashop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.dmuszynski.aquashop.entity.Person;
import pl.dmuszynski.aquashop.entity.Role;
import pl.dmuszynski.aquashop.entity.User;
import pl.dmuszynski.aquashop.repository.RoleRepository;
import pl.dmuszynski.aquashop.repository.UserRepository;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;

@Service
public class UserManager {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserManager(final UserRepository userRepository, final RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public User register(String email, String password) {
        User registerUser = new User.UserBuilder(email, password)
            .roles(new HashSet<>(
                Collections.singletonList(
                    this.roleRepository.findByName("ROLE_USER")
                )))
            .build();

        return userRepository.save(registerUser);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fillDb(){
//        Role r1 = new Role();
//        Role r2 = new Role();
//        r1.setName("ROLE_USER");
//        r2.setName("ROLE_ADMIN");
//
//        roleRepository.save(r1);
//        roleRepository.save(r2);
//        this.userRepository.delete((this.userRepository.findById(2L)).get());

        register("email1122", "password");
    }
}
