package pl.dmuszynski.aquashop.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import pl.dmuszynski.aquashop.service.AdminService;
import pl.dmuszynski.aquashop.service.UserService;
import pl.dmuszynski.aquashop.service.RoleService;
import pl.dmuszynski.aquashop.payload.UserDTO;
import pl.dmuszynski.aquashop.model.User;

import javax.transaction.Transactional;
import java.util.stream.Collectors;
import java.util.List;

import lombok.RequiredArgsConstructor;

@Service(value = "adminService")
public class AdminServiceImpl implements AdminService {

    private final UserService userService;
    private final RoleService roleService;
    private final ModelMapper modelMapper;

    @Autowired
    public AdminServiceImpl(UserService userService, RoleService roleService, ModelMapper modelMapper) {
        this.userService = userService;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
    }

    @Transactional
    @Override
    public UserDTO updateUser(UserDTO userDetails, Long id) {
        final User foundUser = this.userService.findUserById(id);
        foundUser.setLocked(userDetails.isLocked());
        foundUser.setRoles(userDetails.getRoles().stream()
            .map(roleDTO -> this.roleService.findByRoleType(roleDTO.getRoleType()))
            .collect(Collectors.toSet())
        );

        final User savedUser = this.userService.save(foundUser);
        return this.modelMapper.map(savedUser, UserDTO.class);
    }

    @Override
    public List<UserDTO> findAllUserDto() {
        return this.userService.findAllUserDto();
    }
}