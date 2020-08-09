package pl.dmuszynski.aquashop.service;

public interface AuthService {
    void registerUser(String email, String password);
    void activateUser(String tokenValue);
    void authenticateUser();
}
