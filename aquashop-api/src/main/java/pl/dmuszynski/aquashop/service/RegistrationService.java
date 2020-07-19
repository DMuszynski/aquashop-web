package pl.dmuszynski.aquashop.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.dmuszynski.aquashop.model.Role;
import pl.dmuszynski.aquashop.repository.RoleRepository;
import pl.dmuszynski.aquashop.repository.UserRepository;
import pl.dmuszynski.aquashop.model.User;

import java.util.Collections;
import java.util.HashSet;

@Service
public class RegistrationService {

    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private TokenService tokenService;

    @Autowired
    public RegistrationService(PasswordEncoder passwordEncoder, UserRepository userRepository,
                               RoleRepository roleRepository, TokenService tokenService)
    {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.tokenService = tokenService;
    }

    public void register(String email, String password) {
        User registerUser = User.builder()
            .email(email)
            .password(passwordEncoder.encode(password))
            .roles(new HashSet<>(
                Collections.singletonList(
                    this.roleRepository.findByName("ROLE_USER")
                        .orElseGet(()->{
                            Role userRole = new Role();
                            userRole.setName("ROLE_USER");
                            return this.roleRepository.save(userRole);
                        })
                )))
            .build();

        userRepository.save(registerUser);
        tokenService.sendToken(registerUser);
    }
}
