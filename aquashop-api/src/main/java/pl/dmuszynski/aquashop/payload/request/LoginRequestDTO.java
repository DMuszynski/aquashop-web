package pl.dmuszynski.aquashop.payload.request;

import lombok.Data;

@Data
public class LoginRequestDTO {
    private String username;
    private String password;
}
