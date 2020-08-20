package pl.dmuszynski.aquashop.payload.request;

import lombok.Data;

@Data
public class SignupRequestDTO {
    private String username;
    private String email;
    private String password;
}
