package pl.dmuszynski.aquashop.payload;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Data;
import pl.dmuszynski.aquashop.model.Role;

import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AuthorizedUserDTO extends UserDTO {

    private List<RoleDTO> roles;

    public AuthorizedUserDTO(Long id, String email, String username, boolean isEnabled, boolean isLocked) {
        super(id, email, username, isEnabled, isLocked);
//        this.roles = roles;
    }

//    public UserDTO(List<Role> roles) {
//        this.id = id;
//        this.email = email;
//        this.roles = roles.stream()
//            .map(role -> new RoleDTO(role.getId(), role.getRoleType()))
//            .collect(Collectors.toList());
//    }
}
