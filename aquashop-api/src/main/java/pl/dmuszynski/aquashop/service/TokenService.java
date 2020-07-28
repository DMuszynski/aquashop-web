package pl.dmuszynski.aquashop.service;

import pl.dmuszynski.aquashop.model.Token;
import pl.dmuszynski.aquashop.model.User;

public interface TokenService {
    Token findByValue(String value);
    void sendToken(User user);
}
