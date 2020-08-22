package pl.dmuszynski.aquashop.payload;

import pl.dmuszynski.aquashop.model.RoleType;

import lombok.Data;

@Data
public class RoleDTO {
    private Long id;
    private RoleType roleType;
}
