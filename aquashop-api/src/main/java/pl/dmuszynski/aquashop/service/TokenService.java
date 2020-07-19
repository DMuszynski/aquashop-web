package pl.dmuszynski.aquashop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dmuszynski.aquashop.entity.Token;
import pl.dmuszynski.aquashop.entity.User;
import pl.dmuszynski.aquashop.repository.TokenRepository;

import javax.mail.MessagingException;
import java.util.UUID;

@Service
public class TokenService {

    private TokenRepository tokenRepository;
    private MailService mailService;

    @Autowired
    public TokenService(TokenRepository tokenRepository, MailService mailService) {
        this.tokenRepository = tokenRepository;
        this.mailService = mailService;
    }

    public Token findTokenByValue(String value) {
        return tokenRepository.findTokenByValue(value);
    }

    public void sendToken(User user) {
        Token userToken = generateNewUserToken(user);
        String mailSubject = "Potwierdzenie rejestracji konta AquaShop";
        String mailContent = "Wymagane potwierdzenie rejestracji. Aby aktywować konto kliknij w poniższy link: \n"
            + "http://localhost:8080/token?value=" + userToken.getValue();

        try {
            mailService.sendMail(user.getEmail(), mailSubject, mailContent, true );
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private Token generateNewUserToken(User user) {
        String tokenValue = UUID.randomUUID().toString();
        Token token = new Token();
        token.setValue(tokenValue);
        token.setUser(user);

        return tokenRepository.save(token);
    }
}
