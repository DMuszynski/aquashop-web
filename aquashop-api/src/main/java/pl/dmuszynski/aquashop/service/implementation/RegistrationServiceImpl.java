package pl.dmuszynski.aquashop.service.implementation;

import pl.dmuszynski.aquashop.exception.UniqueEmailException;
import pl.dmuszynski.aquashop.exception.EnabledException;
import pl.dmuszynski.aquashop.exception.UniqueUsernameException;
import pl.dmuszynski.aquashop.payload.UserDTO;
import pl.dmuszynski.aquashop.payload.request.SignupRequestDTO;
import pl.dmuszynski.aquashop.service.RegistrationService;
import pl.dmuszynski.aquashop.repository.UserRepository;
import pl.dmuszynski.aquashop.service.TokenService;
import pl.dmuszynski.aquashop.service.RoleService;
import pl.dmuszynski.aquashop.model.RoleType;
import pl.dmuszynski.aquashop.model.Token;
import pl.dmuszynski.aquashop.model.User;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.HashSet;

@Service(value = "registrationService")
public class RegistrationServiceImpl implements RegistrationService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final RoleService roleService;
    private final ModelMapper modelMapper;

    @Autowired
    public RegistrationServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository,
                                   TokenService tokenService, RoleService roleService,
                                   ModelMapper modelMapper)
    {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDTO registerUser(SignupRequestDTO signupDetails) {
        validateUsernameAlreadyExist(signupDetails.getUsername());
        validateUserEmailAlreadyExist(signupDetails.getEmail());

        final User registerUser = this.userRepository
            .save(new User(
                signupDetails.getEmail(),
                signupDetails.getUsername(),
                this.passwordEncoder.encode(signupDetails.getPassword()),
                new HashSet<>(Collections.singletonList(this.roleService.findByRoleType(RoleType.ROLE_USER))))
            );

        this.tokenService.sendToken(registerUser);
        return this.modelMapper.map(registerUser, UserDTO.class);
    }

    @Transactional
    @Override
    public void activateAccountByUserToken(String tokenValue) {
        final Token foundToken = this.tokenService.findByValue(tokenValue);
        final User tokenUser = foundToken.getUser();

        if (!tokenUser.isEnabled())
            this.userRepository.activateAccount(tokenUser.getId());
        else
            throw new EnabledException();
    }

    private void validateUserEmailAlreadyExist(String email) throws UniqueEmailException {
        if (this.userRepository.findByEmail(email).isPresent())
            throw new UniqueEmailException("The user with given address e-mail " + email + " is already exist");
    }

    private void validateUsernameAlreadyExist(String username) throws UniqueUsernameException {
        if (this.userRepository.findByUsername(username).isPresent())
            throw new UniqueUsernameException("The user with given username " + username + " is already exist");
    }
}
