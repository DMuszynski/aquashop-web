package pl.dmuszynski.aquashop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.dmuszynski.aquashop.exception.TokenNotFoundException;
import pl.dmuszynski.aquashop.repository.TokenRepository;
import pl.dmuszynski.aquashop.model.Token;
import pl.dmuszynski.aquashop.model.User;

import javax.mail.MessagingException;
import java.util.UUID;

@Service
public class TokenService implements ITokenService {

    private final TokenRepository tokenRepository;
    private final IMailService mailService;

    @Autowired
    public TokenService(TokenRepository tokenRepository, IMailService mailService) {
        this.tokenRepository = tokenRepository;
        this.mailService = mailService;
    }

    @Override
    public Token findByValue(String value) {
        return tokenRepository.findByValue(value)
            .orElseThrow(() -> new TokenNotFoundException(value));
    }

    @Override
    public void sendToken(User user) {
        Token userToken = generateNewUserToken(user);
        String mailSubject = "Potwierdzenie rejestracji konta AquaShop";
        String mailContent = "Wymagane potwierdzenie rejestracji. Aby aktywować konto kliknij w poniższy link: \n"
            + "http://localhost:8080/user/token?value=" + userToken.getValue();

        try {
            mailService.sendMail(user.getEmail(), mailSubject, mailContent, true );

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private Token generateNewUserToken(User user) {
        String tokenValue;
        do tokenValue = UUID.randomUUID().toString();
        while (tokenRepository.findByValue(tokenValue).isPresent());

        Token token = new Token(user, tokenValue);
        return tokenRepository.save(token);
    }
}
