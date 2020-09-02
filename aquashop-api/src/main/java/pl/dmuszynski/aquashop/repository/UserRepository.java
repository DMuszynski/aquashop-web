package pl.dmuszynski.aquashop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.dmuszynski.aquashop.model.User;
import pl.dmuszynski.aquashop.payload.UserDTO;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

//    @Query("SELECT new pl.dmuszynski.aquashop.payload.UserDTO(r) FROM User u join u.roles r WHERE u.id = :id and r.id = :id")

    boolean existsByEmail(String username);

    boolean existsByUsername(String username);

    @Modifying @Query(value = "UPDATE User u SET u.email = :email WHERE u.id = :id")
    void updateEmailById(@Param("email") String email, @Param("id") Long id);

    @Modifying @Query(value = "UPDATE User u SET u.password = :password WHERE u.id = :id")
    void updatePasswordById(@Param("password") String password, @Param("id") Long id);

    @Modifying @Query(value = "UPDATE User u SET u.isEnabled = true WHERE u.id = :id")
    void activateAccount(@Param("id") Long id);

    @Query(value = "SELECT new pl.dmuszynski.aquashop.payload.UserDTO(u.id, u.email, u.username, u.isEnabled, u.isLocked) FROM User u WHERE u.id =:id")
    Optional<UserDTO> findUserDtoById(@Param("id") Long id);

    Optional<User> findByUsername(String username);
}
