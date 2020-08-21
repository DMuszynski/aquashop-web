package pl.dmuszynski.aquashop.service.implementation;

import org.modelmapper.ModelMapper;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.dmuszynski.aquashop.payload.UserDTO;
import pl.dmuszynski.aquashop.repository.AdminRepository;
import pl.dmuszynski.aquashop.repository.RoleRepository;
import pl.dmuszynski.aquashop.repository.UserRepository;
import pl.dmuszynski.aquashop.service.AdminService;
import pl.dmuszynski.aquashop.model.RoleType;
import pl.dmuszynski.aquashop.model.User;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service(value = "adminService")
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository, UserRepository userRepository,
                            RoleRepository roleRepository, ModelMapper modelMapper)
    {
        this.adminRepository = adminRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<UserDTO> findAllByUserId() {
        return this.adminRepository.findAll()
            .stream()
            .map(user -> this.modelMapper.map(user, UserDTO.class))
            .collect(Collectors.toList());
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
