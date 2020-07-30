package pl.dmuszynski.aquashop.service;

import pl.dmuszynski.aquashop.model.RoleType;
import pl.dmuszynski.aquashop.model.User;

public interface AdminService {
    void updateUserIsEnabledById(boolean isEnabled, Long id);
    void updateUserRole(RoleType roleType, Long userId);
    Iterable<User> findAll();
}
