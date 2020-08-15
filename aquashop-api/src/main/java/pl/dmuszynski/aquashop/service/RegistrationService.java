package pl.dmuszynski.aquashop.service;

import pl.dmuszynski.aquashop.model.User;

public interface RegistrationService {
    User register(String email, String password);
    void activateAccountByUserToken(String value);
}
