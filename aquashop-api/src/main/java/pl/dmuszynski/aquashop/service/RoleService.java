package pl.dmuszynski.aquashop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dmuszynski.aquashop.model.Role;
import pl.dmuszynski.aquashop.model.RoleType;
import pl.dmuszynski.aquashop.repository.RoleRepository;

@Service
public class RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role findByRoleType(RoleType roleType) {
        return roleRepository.findByRoleType(roleType)
            .orElseGet(() -> roleRepository.save(new Role(roleType)));
    }
}
