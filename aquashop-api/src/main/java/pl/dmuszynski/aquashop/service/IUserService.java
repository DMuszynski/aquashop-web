package pl.dmuszynski.aquashop.service;

import pl.dmuszynski.aquashop.model.User;

public interface IUserService {
    void changePassword(String newPassword, Long id);
    void changeEmail(String email, Long id);

    User findById(Long id);
    void deleteById(Long id);
}
