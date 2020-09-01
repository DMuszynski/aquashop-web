package pl.dmuszynski.aquashop.payload;

import java.util.List;
import java.util.stream.Collectors;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import pl.dmuszynski.aquashop.model.Role;

@Data
//@RequiredArgsConstructor
public class UserDTO {
    private Long id;
    private String email;
    private String username;
    private List<RoleDTO> roles;
    private boolean isEnabled;
    private boolean isLocked;

//    public UserDTO(List<Role> roles) {
//        this.id = id;
//        this.email = email;
//        this.roles = roles.stream()
//            .map(role -> new RoleDTO(role.getId(), role.getRoleType()))
//            .collect(Collectors.toList());
//    }
}