package pl.dmuszynski.aquashop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.dmuszynski.aquashop.payload.AuthorizedUserDTO;
import pl.dmuszynski.aquashop.model.User;

import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<User, Long> {
//    @Query(value = "SELECT new pl.dmuszynski.aquashop.payload.AuthorizedUserDTO(u.id, u.email, u.username, u.isEnabled, u.isLocked, r) FROM User u JOIN FETCH u.roles r")
    @Query(value = "SELECT new pl.dmuszynski.aquashop.payload.AuthorizedUserDTO(u.id, u.email, u.username, u.isEnabled, u.isLocked) " +
        "FROM User u")
    List<AuthorizedUserDTO> findAllAuthorizedUserDto();
}
