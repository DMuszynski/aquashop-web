package pl.dmuszynski.aquashop.service.implementation;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.dmuszynski.aquashop.repository.AdminRepository;
import pl.dmuszynski.aquashop.repository.RoleRepository;
import pl.dmuszynski.aquashop.repository.UserRepository;
import pl.dmuszynski.aquashop.service.AdminService;
import pl.dmuszynski.aquashop.model.RoleType;
import pl.dmuszynski.aquashop.model.User;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service(value = "adminService")
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository, UserRepository userRepository,
                            RoleRepository roleRepository)
    {
        this.adminRepository = adminRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<User> findAll() {
        return this.adminRepository.findAll();
    }

    @Override
    public User updateUserIsEnabledById(boolean isEnabled, Long id) throws ResourceNotFoundException {
        final User user = this.userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User not found for this id: " + id));

        this.adminRepository.updateUserIsEnabledById(isEnabled, id);
        return user;
    }

    @Override
    public User updateUserRole(RoleType roleType, Long id) throws ResourceNotFoundException {
        final User user = this.userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User not found for this id: " + id));

        this.adminRepository.updateUserRole(2,1);
        return user;
    }
}
