package pl.dmuszynski.aquashop.service;

import pl.dmuszynski.aquashop.model.User;

public interface IUserService {
    User save(User user);
    User findByEmail(String email);
    void updateIsEnabledById(boolean isEnabled, Long id);
    void deleteById(Long id);
}
