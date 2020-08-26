package pl.dmuszynski.aquashop.service;

import pl.dmuszynski.aquashop.payload.UserDTO;
import pl.dmuszynski.aquashop.payload.request.SignupRequestDTO;

public interface RegistrationService {
    UserDTO registerUser(SignupRequestDTO signupDetails);
    void activateAccountByUserToken(String value);
}
