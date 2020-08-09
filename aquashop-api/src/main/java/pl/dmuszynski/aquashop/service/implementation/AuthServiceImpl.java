package pl.dmuszynski.aquashop.service.implementation;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.dmuszynski.aquashop.exception.UserEmailAlreadyExistException;
import pl.dmuszynski.aquashop.exception.UserIsAlreadyEnabledException;
import pl.dmuszynski.aquashop.model.Token;
import pl.dmuszynski.aquashop.payload.request.LoginRequest;
import pl.dmuszynski.aquashop.payload.response.JwtResponse;
import pl.dmuszynski.aquashop.repository.UserRepository;
import pl.dmuszynski.aquashop.security.jwt.JwtUtils;
import pl.dmuszynski.aquashop.security.services.UserDetailsImpl;
import pl.dmuszynski.aquashop.service.TokenService;
import pl.dmuszynski.aquashop.service.AuthService;
import pl.dmuszynski.aquashop.service.RoleService;
import pl.dmuszynski.aquashop.model.RoleType;
import pl.dmuszynski.aquashop.model.User;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final RoleService roleService;
    private final JwtUtils jwtUtils;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder,
                           UserRepository userRepository, TokenService tokenService,
                           RoleService roleService, JwtUtils jwtUtils)
    {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.roleService = roleService;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public JwtResponse authenticateUser(User user) {
        final Authentication authentication = this.authenticationManager
            .authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
            .map(item -> item.getAuthority())
            .collect(Collectors.toList());

        return new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(),
            userDetails.getEmail(), roles);
    }

    @Override
    public void registerUser(String email, String password) {
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
    public void activateUser(String tokenValue) {
        final Token tokenByValue = this.tokenService.findByValue(tokenValue);
        final User user = tokenByValue.getUser();

        if (!user.isEnabled())
            this.userRepository.activateAccount(user.getId());
        else
            throw new UserIsAlreadyEnabledException();
    }

    private void validateUserEmailAlreadyExist(String email) {
        if (this.userRepository.findByEmail(email).isPresent())
            throw new UserEmailAlreadyExistException(email);
    }
}
