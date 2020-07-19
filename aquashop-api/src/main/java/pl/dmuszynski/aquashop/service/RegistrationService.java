package pl.dmuszynski.aquashop.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.dmuszynski.aquashop.entity.Token;
import pl.dmuszynski.aquashop.entity.User;
import pl.dmuszynski.aquashop.repository.RoleRepository;
import pl.dmuszynski.aquashop.repository.TokenRepository;
import pl.dmuszynski.aquashop.repository.UserRepository;

import javax.mail.MessagingException;
import java.util.Collections;
import java.util.HashSet;
import java.util.UUID;

@Service
public class RegistrationService {

    private MailService mailService;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private TokenRepository tokenRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(MailService mailService, UserRepository userRepository, RoleRepository roleRepository,
                               TokenRepository tokenRepository, PasswordEncoder passwordEncoder)
    {
        this.mailService = mailService;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.tokenRepository = tokenRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(String email, String password) {
        User registerUser = User.builder()
            .email(email)
            .password(passwordEncoder.encode(password))
            .roles(new HashSet<>(
                Collections.singletonList(
                    this.roleRepository.findByName("ROLE_USER")
                )))
            .build();

        userRepository.save(registerUser);
        sendToken(registerUser);
    }

    private void sendToken(User registerUser) {
        Token userToken = generateNewUserToken(registerUser);
        String mailSubject = "Potwierdzenie rejestracji konta AquaShop";
        String mailContent = "Wymagane potwierdzenie rejestracji. Aby aktywować konto kliknij w poniższy link: \n"
            + "http://localhost:8080/activatelink/" + userToken.getValue();

        try {
            mailService.sendMail(registerUser.getEmail(), mailSubject, mailContent, true );
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private Token generateNewUserToken(User registerUser) {
        String tokenValue = UUID.randomUUID().toString();
        Token token = new Token();
        token.setValue(tokenValue);
        token.setUser(registerUser);

        return tokenRepository.save(token);
    }
}
