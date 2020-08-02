package pl.dmuszynski.aquashop.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dmuszynski.aquashop.exception.UserNotFoundException;
import pl.dmuszynski.aquashop.repository.AdminRepository;
import pl.dmuszynski.aquashop.repository.RoleRepository;
import pl.dmuszynski.aquashop.service.AdminService;
import pl.dmuszynski.aquashop.model.RoleType;
import pl.dmuszynski.aquashop.model.User;

import javax.transaction.Transactional;
import java.util.List;

@Service @Transactional
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository, RoleRepository roleRepository) {
        this.adminRepository = adminRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<User> findAll() {
        return this.adminRepository.findAll();
    }

    @Override
    public void updateUserIsEnabledById(boolean isEnabled, Long id) {
        this.adminRepository.updateUserIsEnabledById(isEnabled, id);
    }

    @Override
    public void updateUserRole(RoleType roleType, Long userId) {
        this.adminRepository.updateUserRole(2,1);
    }
}
