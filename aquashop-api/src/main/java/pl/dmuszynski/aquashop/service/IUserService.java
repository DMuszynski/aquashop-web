package pl.dmuszynski.aquashop.service;

import pl.dmuszynski.aquashop.model.User;

public interface IUserService {
    User save(User user);
    void deleteById(Long id);
}
