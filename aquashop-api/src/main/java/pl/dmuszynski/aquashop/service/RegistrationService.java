package pl.dmuszynski.aquashop.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.dmuszynski.aquashop.exception.UserEmailAlreadyExistException;
import pl.dmuszynski.aquashop.repository.UserRepository;
import pl.dmuszynski.aquashop.model.RoleType;
import pl.dmuszynski.aquashop.model.Token;
import pl.dmuszynski.aquashop.model.User;

import java.util.Collections;
import java.util.HashSet;

@Service
public class RegistrationService implements IRegistrationService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final ITokenService tokenService;
    private final IRoleService roleService;

    @Autowired
    public RegistrationService(PasswordEncoder passwordEncoder, UserRepository userRepository,
                               ITokenService tokenService, IRoleService roleService)
    {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.roleService = roleService;
    }

    @Override
    public void register(String email, String password) {
        this.userRepository.findByEmail(email)
            .orElseThrow(() -> new UserEmailAlreadyExistException(email));

        User registerUser = new User.UserBuilder(email, passwordEncoder.encode(password))
            .roles(new HashSet<>(
                Collections.singletonList(
                    this.roleService.findByRoleType(RoleType.ROLE_USER)
                )))
            .build();

        userRepository.save(registerUser);
        tokenService.sendToken(registerUser);
    }

    @Override
    public void signUp(String tokenValue) {
        Token tokenByValue = tokenService.findByValue(tokenValue);
        User user = tokenByValue.getUser();

        userRepository.updateIsEnabledById(true, user.getId());
    }
}
