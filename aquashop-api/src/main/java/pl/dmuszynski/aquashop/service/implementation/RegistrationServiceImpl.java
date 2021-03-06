package pl.dmuszynski.aquashop.service.implementation;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import pl.dmuszynski.aquashop.validator.UniqueUsernameValidator;
import pl.dmuszynski.aquashop.validator.UniqueEmailValidator;
import pl.dmuszynski.aquashop.payload.UserDTO;
import pl.dmuszynski.aquashop.service.RegistrationService;
import pl.dmuszynski.aquashop.service.TokenService;
import pl.dmuszynski.aquashop.service.RoleService;
import pl.dmuszynski.aquashop.exception.AlreadyEnabledException;
import pl.dmuszynski.aquashop.repository.UserRepository;
import pl.dmuszynski.aquashop.model.RoleType;
import pl.dmuszynski.aquashop.model.Token;
import pl.dmuszynski.aquashop.model.User;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;
import java.util.Collections;

@Transactional
@RequiredArgsConstructor
@Service(value = "registrationService")
public class RegistrationServiceImpl implements RegistrationService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final RoleService roleService;
    private final ModelMapper modelMapper;

    @Override
    public UserDTO registerUser(String email, String username, String password) {
        new UniqueUsernameValidator(userRepository).validate(username);
        new UniqueEmailValidator(userRepository).validate(email);

        final User registerUser = this.userRepository
            .save(User.builder()
                .email(email)
                .username(username)
                .password(this.passwordEncoder.encode(password))
                .roles(Collections.singletonList(this.roleService.findByRoleType(RoleType.ROLE_USER)))
                .build()
            );

//        this.tokenService.sendToken(registerUser);
        return this.modelMapper.map(registerUser, UserDTO.class);
    }

    @Override
    public void activateAccountByUserToken(String tokenValue) {
        final Token foundToken = this.tokenService.findByValue(tokenValue);
        final User tokenUser = foundToken.getUser();

        if (!tokenUser.isEnabled())
            this.userRepository.activateAccount(tokenUser.getId());
        else
            throw new AlreadyEnabledException();
    }
}
