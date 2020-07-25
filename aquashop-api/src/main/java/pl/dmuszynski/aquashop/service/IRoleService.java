package pl.dmuszynski.aquashop.service;

import pl.dmuszynski.aquashop.model.Role;
import pl.dmuszynski.aquashop.model.RoleType;

public interface IRoleService {
    Role findByRoleType(RoleType roleType);
}
