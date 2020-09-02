package pl.dmuszynski.aquashop.payload;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements Serializable {
    private Long id;
    private String email;
    private String username;
    private boolean isEnabled;
    private boolean isLocked;
}