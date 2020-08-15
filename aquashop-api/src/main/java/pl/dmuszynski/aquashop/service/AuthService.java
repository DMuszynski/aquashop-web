package pl.dmuszynski.aquashop.service;

import pl.dmuszynski.aquashop.model.User;
import pl.dmuszynski.aquashop.payload.request.LoginRequest;
import pl.dmuszynski.aquashop.payload.response.JwtResponse;

public interface AuthService {
    JwtResponse authenticateUser(User user);
}
