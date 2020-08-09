package pl.dmuszynski.aquashop.service;

import pl.dmuszynski.aquashop.model.User;
import pl.dmuszynski.aquashop.payload.request.LoginRequest;
import pl.dmuszynski.aquashop.payload.response.JwtResponse;

public interface AuthService {
    void registerUser(String email, String password);
    void activateUser(String tokenValue);
    JwtResponse authenticateUser(User user);
}
