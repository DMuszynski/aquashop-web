package pl.dmuszynski.aquashop.service.implementation;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import pl.dmuszynski.aquashop.repository.TokenRepository;
import pl.dmuszynski.aquashop.service.TokenService;
import pl.dmuszynski.aquashop.service.MailService;
import pl.dmuszynski.aquashop.model.Token;
import pl.dmuszynski.aquashop.model.User;
import lombok.RequiredArgsConstructor;

import javax.mail.MessagingException;
import java.util.UUID;

@RequiredArgsConstructor
@Service(value = "tokenService")
public class TokenServiceImpl implements TokenService {

    private final TokenRepository tokenRepository;
    private final MailService mailService;

    @Override
    public Token findByValue(String value) {
        return tokenRepository.findByValue(value)
            .orElseThrow(() -> new ResourceNotFoundException("Token not found for this value: " + value));
    }

    @Override
    public void sendToken(User user) {
        final Token userToken = this.generateNewUserToken(user);
        final String mailSubject = "Potwierdzenie rejestracji konta AquaShop";
        final String mailContent = "Wymagane potwierdzenie rejestracji. Aby aktywować konto kliknij w poniższy link: \n"
            + "http://localhost:8080/auth-management/token?value=" + userToken.getValue();

        try {
            this.mailService.sendMail(user.getEmail(), mailSubject, mailContent, true );
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private Token generateNewUserToken(User user) {
        String tokenValue;
        do tokenValue = UUID.randomUUID().toString();
        while (tokenRepository.existsTokenByValue(tokenValue));

        final Token token = new Token(user, tokenValue);
        return tokenRepository.save(token);
    }
}
