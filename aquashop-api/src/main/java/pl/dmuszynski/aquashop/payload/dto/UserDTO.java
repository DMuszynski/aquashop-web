package pl.dmuszynski.aquashop.payload.dto;

import java.util.List;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String email;
    private String username;
    private List<String> roles;
    private boolean isEnabled;
}
