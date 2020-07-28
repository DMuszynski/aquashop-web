package pl.dmuszynski.aquashop.service;

import pl.dmuszynski.aquashop.model.User;

public interface UserService {
    void changePassword(String password, Long id);
    void changeEmail(String email, Long id);
    void deleteById(Long id);
    User findById(Long id);
}
