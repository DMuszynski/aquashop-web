package pl.dmuszynski.aquashop.service;

import pl.dmuszynski.aquashop.payload.request.LoginRequestDTO;
import pl.dmuszynski.aquashop.payload.response.JwtResponseDTO;

public interface AuthService {
    JwtResponseDTO authenticateUser(LoginRequestDTO loginDetails);
}
