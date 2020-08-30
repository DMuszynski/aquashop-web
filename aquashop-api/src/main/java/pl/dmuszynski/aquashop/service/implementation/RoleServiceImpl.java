package pl.dmuszynski.aquashop.service.implementation;

import pl.dmuszynski.aquashop.repository.RoleRepository;
import pl.dmuszynski.aquashop.service.RoleService;
import pl.dmuszynski.aquashop.model.RoleType;
import pl.dmuszynski.aquashop.model.Role;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service(value = "roleService")
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role findByRoleType(RoleType roleType) {
        return roleRepository.findByRoleType(roleType)
            .orElseGet(() -> roleRepository.save(new Role(roleType)));
    }
}
