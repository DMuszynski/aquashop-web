package pl.dmuszynski.aquashop.payload.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    private Long id;
    private String email;
    private String username;
    private List<String> roles;
    private boolean isEnabled;
}
