package pl.dmuszynski.aquashop.service;

import pl.dmuszynski.aquashop.model.User;

public interface UserService {
    User findById(Long id);
    User changePassword(String password, Long id);
    User changeEmail(String email, Long id);
    void deleteById(Long id);
}
