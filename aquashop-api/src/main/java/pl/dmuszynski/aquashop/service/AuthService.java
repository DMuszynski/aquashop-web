package pl.dmuszynski.aquashop.service;

import pl.dmuszynski.aquashop.payload.response.JwtResponse;
import pl.dmuszynski.aquashop.model.User;

public interface AuthService {
    JwtResponse authenticateUser(User user);
}
