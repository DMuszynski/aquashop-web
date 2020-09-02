package pl.dmuszynski.aquashop.payload;

import pl.dmuszynski.aquashop.model.RoleType;
import lombok.RequiredArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@RequiredArgsConstructor
public class RoleDTO implements Serializable {
    private Long id;
    private RoleType roleType;

    public RoleDTO(Long id, RoleType roleType) {
        this.id = id;
        this.roleType = roleType;
    }
}
