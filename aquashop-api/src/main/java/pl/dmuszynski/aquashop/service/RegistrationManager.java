package pl.dmuszynski.aquashop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.dmuszynski.aquashop.entity.User;
import pl.dmuszynski.aquashop.repository.RoleRepository;
import pl.dmuszynski.aquashop.repository.UserRepository;

import java.util.Collections;
import java.util.HashSet;

@Service
public class RegistrationManager {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder encoder;

    @Autowired
    public RegistrationManager(UserRepository userRepository, RoleRepository roleRepository,
                               PasswordEncoder encoder)
    {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
    }

    public User register(String email, String password) {
        User registerUser = new User.UserBuilder(email, encoder.encode(password))
            .roles(new HashSet<>(
                Collections.singletonList(
                    this.roleRepository.findByName("ROLE_USER")
                )))
            .build();

        return userRepository.save(registerUser);
    }
}
