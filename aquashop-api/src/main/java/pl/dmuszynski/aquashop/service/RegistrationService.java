package pl.dmuszynski.aquashop.service;

import pl.dmuszynski.aquashop.payload.UserDTO;

public interface RegistrationService {
    UserDTO registerUser(String email, String username, String password);
    void activateAccountByUserToken(String value);
}
