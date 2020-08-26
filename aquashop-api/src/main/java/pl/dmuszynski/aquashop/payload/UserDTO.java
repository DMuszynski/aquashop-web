package pl.dmuszynski.aquashop.payload;

import java.util.List;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String email;
    private String username;
    private List<RoleDTO> roles;
    private boolean isEnabled;
    private boolean isLocked;
}