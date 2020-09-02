package pl.dmuszynski.aquashop.service.implementation;

import pl.dmuszynski.aquashop.payload.AuthorizedUserDTO;
import pl.dmuszynski.aquashop.repository.AdminRepository;
import pl.dmuszynski.aquashop.service.AdminService;
import pl.dmuszynski.aquashop.service.UserService;
import pl.dmuszynski.aquashop.service.RoleService;
import pl.dmuszynski.aquashop.payload.UserDTO;
import pl.dmuszynski.aquashop.model.User;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;
import java.util.stream.Collectors;
import java.util.List;

@RequiredArgsConstructor
@Service(value = "adminService")
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final UserService userService;
    private final RoleService roleService;
    private final ModelMapper modelMapper;

    @Transactional
    @Override
    public UserDTO updateUser(UserDTO userDetails, Long id) {
        final User foundUser = this.userService.findUserById(id);
        foundUser.setLocked(userDetails.isLocked());
//        foundUser.setRoles(userDetails.getRoles().stream()
//            .map(roleDTO -> this.roleService.findByRoleType(roleDTO.getRoleType()))
//            .collect(Collectors.toList())
//        );

        final User savedUser = this.adminRepository.save(foundUser);
        return this.modelMapper.map(savedUser, UserDTO.class);
    }

    @Override
    public List<AuthorizedUserDTO> findAllAuthorizedUserDto() {
        return this.adminRepository.findAllAuthorizedUserDto();
    }
}