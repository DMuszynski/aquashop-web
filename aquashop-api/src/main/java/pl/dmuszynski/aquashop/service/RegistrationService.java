package pl.dmuszynski.aquashop.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.dmuszynski.aquashop.repository.UserRepository;
import pl.dmuszynski.aquashop.model.RoleType;
import pl.dmuszynski.aquashop.model.Token;
import pl.dmuszynski.aquashop.model.User;

import java.util.Collections;
import java.util.HashSet;

@Service
public class RegistrationService {

    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private TokenService tokenService;
    private RoleService roleService;

    @Autowired
    public RegistrationService(PasswordEncoder passwordEncoder, UserRepository userRepository,
                               TokenService tokenService, RoleService roleService)
    {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.roleService = roleService;
    }

    public void register(String email, String password) {
//        User registerUser = User.builder()
////            .email(email)
////            .password(passwordEncoder.encode(password))
////            .roles(new HashSet<>(
////                Collections.singletonList(
////                    this.roleService.findByRoleType(RoleType.USER)
////                )))
////            .build();


        User registerUser = new User();
        registerUser.setEmail(email);
        registerUser.setPassword(passwordEncoder.encode(password));
        registerUser.setRoles(new HashSet<>(
            Collections.singletonList(
                this.roleService.findByRoleType(RoleType.ROLE_USER)
            )));

        userRepository.save(registerUser);
        tokenService.sendToken(registerUser);
    }

    public void signUp(String tokenValue) {
        Token tokenByValue = tokenService.findTokenByValue(tokenValue);
        User user = tokenByValue.getUser();

        System.out.println("JEST");
        userRepository.save(user);
    }
}
