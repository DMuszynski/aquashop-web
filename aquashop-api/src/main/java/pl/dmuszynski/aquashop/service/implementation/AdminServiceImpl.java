package pl.dmuszynski.aquashop.service.implementation;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import pl.dmuszynski.aquashop.model.RoleType;
import pl.dmuszynski.aquashop.repository.AdminRepository;
import pl.dmuszynski.aquashop.service.AdminService;
import pl.dmuszynski.aquashop.service.UserService;
import pl.dmuszynski.aquashop.service.RoleService;
import pl.dmuszynski.aquashop.payload.UserDTO;
import pl.dmuszynski.aquashop.model.User;
import pl.dmuszynski.aquashop.model.Role;

import javax.transaction.Transactional;
import java.util.stream.Collectors;
import java.util.List;

@Transactional
@Service(value = "adminService")
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final UserService userService;
    private final RoleService roleService;
    private final ModelMapper modelMapper;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository, UserService userService, RoleService roleService,
                            ModelMapper modelMapper)
    {
        this.adminRepository = adminRepository;
        this.userService = userService;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDTO updateUserIsEnabledById(boolean isEnabled, Long id) throws ResourceNotFoundException {
        final User foundUser = this.userService.findUserById(id);
        foundUser.setEnabled(isEnabled);

        this.adminRepository.updateUserIsEnabledById(isEnabled, id);
        return this.modelMapper.map(foundUser, UserDTO.class);
    }

    @Override
    public UserDTO updateUserRole(RoleType roleType, Long id) throws ResourceNotFoundException {
        final User foundUser = this.userService.findUserById(id);
        final Role foundRole = this.roleService.findByRoleType(roleType);
        foundUser.getRoles().add(foundRole);

        this.adminRepository.updateUserRole(foundRole.getId(),foundUser.getId());
        return this.modelMapper.map(foundUser, UserDTO.class);
    }

    @Override
    public List<UserDTO> findAll() {
        return this.adminRepository.findAll()
            .stream()
            .map(user -> this.modelMapper.map(user, UserDTO.class))
            .collect(Collectors.toList());
    }
}
