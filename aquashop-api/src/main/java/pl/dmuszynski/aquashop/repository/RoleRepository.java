package pl.dmuszynski.aquashop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.dmuszynski.aquashop.model.RoleType;
import pl.dmuszynski.aquashop.model.Role;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    Optional<Role> findByRoleType(RoleType roleType);
}
