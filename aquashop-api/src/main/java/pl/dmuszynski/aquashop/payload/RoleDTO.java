package pl.dmuszynski.aquashop.payload;

import pl.dmuszynski.aquashop.model.RoleType;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RoleDTO {
    private Long id;
    private RoleType roleType;

    public RoleDTO(Long id, RoleType roleType) {
        this.id = id;
        this.roleType = roleType;
    }
}
