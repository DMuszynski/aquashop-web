package pl.dmuszynski.aquashop.service.implementation;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import pl.dmuszynski.aquashop.payload.request.LoginRequestDTO;
import pl.dmuszynski.aquashop.security.userdetails.UserDetailsImpl;
import pl.dmuszynski.aquashop.security.jwt.JwtUtils;
import pl.dmuszynski.aquashop.payload.response.JwtResponseDTO;
import pl.dmuszynski.aquashop.service.AuthService;
import lombok.RequiredArgsConstructor;

import java.util.stream.Collectors;
import java.util.List;

@RequiredArgsConstructor
@Service(value = "authService")
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @Override
    public JwtResponseDTO authenticateUser(LoginRequestDTO loginDetails) {
        final Authentication authentication = this.authenticationManager
            .authenticate(new UsernamePasswordAuthenticationToken(
                loginDetails.getUsername(),
                loginDetails.getPassword())
            );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String jwtToken = jwtUtils.generateJwtToken(authentication);

        final UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        final List<String> roles = userDetails.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.toList());

        return new JwtResponseDTO(jwtToken, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles);
    }
}
