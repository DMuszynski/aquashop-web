package pl.dmuszynski.aquashop.service.implementation;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.dmuszynski.aquashop.exception.UserEmailAlreadyExistException;
import pl.dmuszynski.aquashop.exception.UserIsAlreadyEnabledException;
import pl.dmuszynski.aquashop.service.RegistrationService;
import pl.dmuszynski.aquashop.repository.UserRepository;
import pl.dmuszynski.aquashop.service.TokenService;
import pl.dmuszynski.aquashop.service.RoleService;
import pl.dmuszynski.aquashop.model.RoleType;
import pl.dmuszynski.aquashop.model.Token;
import pl.dmuszynski.aquashop.model.User;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.HashSet;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final RoleService roleService;

    @Autowired
    public RegistrationServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository,
                                   TokenService tokenService, RoleService roleService)
    {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.roleService = roleService;
    }

    @Override
    public void register(String email, String password) {
        validateUserEmailAlreadyExist(email);
        final User registerUser = new User.UserBuilder(email, this.passwordEncoder.encode(password))
            .roles(new HashSet<>(
                Collections.singletonList(
                    this.roleService.findByRoleType(RoleType.ROLE_USER)
                )))
            .build();

        this.userRepository.save(registerUser);
        this.tokenService.sendToken(registerUser);
    }

    @Transactional
    @Override
    public void signUp(String tokenValue) {
        final Token tokenByValue = this.tokenService.findByValue(tokenValue);
        final User user = tokenByValue.getUser();

        this.activateAccount(user.isEnabled(), user.getId());
    }

    private void activateAccount(boolean isEnabled, Long id) {
        if (!isEnabled)
            this.userRepository.updateIsEnabledById(true, id);
        else
            throw new UserIsAlreadyEnabledException();
    }

    private void validateUserEmailAlreadyExist(String email) {
        if (this.userRepository.findByEmail(email).isPresent())
            throw new UserEmailAlreadyExistException(email);
    }
}