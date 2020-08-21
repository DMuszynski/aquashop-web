package pl.dmuszynski.aquashop.service;

import pl.dmuszynski.aquashop.payload.UserDTO;
import pl.dmuszynski.aquashop.model.RoleType;
import pl.dmuszynski.aquashop.model.User;

import java.util.List;

public interface AdminService {
    List<UserDTO> findAllByUserId();
    User updateUserRole(RoleType roleType, Long id);
    User updateUserIsEnabledById(boolean isEnabled, Long id);
}
