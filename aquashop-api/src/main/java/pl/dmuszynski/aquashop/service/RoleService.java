package pl.dmuszynski.aquashop.service;

import pl.dmuszynski.aquashop.model.Role;
import pl.dmuszynski.aquashop.model.RoleType;

public interface RoleService {
    Role findByRoleType(RoleType roleType);
}
