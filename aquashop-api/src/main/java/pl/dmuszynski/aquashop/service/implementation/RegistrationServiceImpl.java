package pl.dmuszynski.aquashop.service.implementation;

import org.modelmapper.ModelMapper;
import pl.dmuszynski.aquashop.exception.UserEmailAlreadyExistException;
import pl.dmuszynski.aquashop.exception.UserIsAlreadyEnabledException;
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
    public UserDTO register(SignupRequestDTO signupDetails) {
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
        final Token tokenByValue = this.tokenService.findByValue(tokenValue);
        final User user = tokenByValue.getUser();

        if (!user.isEnabled())
            this.userRepository.activateAccount(user.getId());
        else
            throw new UserIsAlreadyEnabledException();
    }

    private void validateUserEmailAlreadyExist(String email) throws UserEmailAlreadyExistException{
        if (this.userRepository.findByEmail(email).isPresent())
            throw new UserEmailAlreadyExistException("The user with given address e-mail " + email + " is already exist");
    }
}
