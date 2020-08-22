package pl.dmuszynski.aquashop.service;

import pl.dmuszynski.aquashop.payload.UserDTO;
import pl.dmuszynski.aquashop.model.RoleType;

import java.util.List;

public interface AdminService {
    UserDTO updateUserRole(RoleType roleType, Long id);
    UserDTO updateUserIsEnabledById(boolean isEnabled, Long id);
    List<UserDTO> findAll();
}
