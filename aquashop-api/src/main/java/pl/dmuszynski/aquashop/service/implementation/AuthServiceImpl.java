package pl.dmuszynski.aquashop.service.implementation;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.dmuszynski.aquashop.payload.request.LoginRequestDTO;
import pl.dmuszynski.aquashop.security.services.UserDetailsImpl;
import pl.dmuszynski.aquashop.security.jwt.JwtUtils;
import pl.dmuszynski.aquashop.payload.response.JwtResponseDTO;
import pl.dmuszynski.aquashop.service.AuthService;

import lombok.RequiredArgsConstructor;

import java.util.stream.Collectors;
import java.util.List;

@Service(value = "authService")
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public JwtResponseDTO authenticateUser(LoginRequestDTO loginDetails) {
        final Authentication authentication = this.authenticationManager
            .authenticate(new UsernamePasswordAuthenticationToken(
                loginDetails.getUsername(),
                loginDetails.getPassword())
            );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.toList());

        return new JwtResponseDTO(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles);
    }
}
