package pl.dmuszynski.aquashop.service;

import pl.dmuszynski.aquashop.payload.UserDTO;
import pl.dmuszynski.aquashop.payload.request.SignupRequestDTO;

public interface RegistrationService {
    UserDTO register(SignupRequestDTO signupDetails);
    void activateAccountByUserToken(String value);
}
