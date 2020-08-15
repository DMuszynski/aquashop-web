package pl.dmuszynski.aquashop.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.dmuszynski.aquashop.repository.RoleRepository;
import pl.dmuszynski.aquashop.service.RoleService;
import pl.dmuszynski.aquashop.model.RoleType;
import pl.dmuszynski.aquashop.model.Role;

@Service(value = "roleService")
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findByRoleType(RoleType roleType) {
        return roleRepository.findByRoleType(roleType)
            .orElseGet(() -> roleRepository
                .save(new Role(roleType)));
    }
}
