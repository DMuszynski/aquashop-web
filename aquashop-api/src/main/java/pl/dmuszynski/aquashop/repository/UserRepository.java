package pl.dmuszynski.aquashop.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.dmuszynski.aquashop.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByEmail(String email);

    @Query("UPDATE User u SET u.email = :email WHERE u.id = :id")
    void updateEmailById(@Param("email") String email, @Param("id") Long id);

    @Query("UPDATE User u SET u.password = :password WHERE u.id = :id")
    void updatePasswordById(@Param("password") String password, @Param("id") Long id);

    @Modifying
    @Query("UPDATE User u SET u.isEnabled = :isEnabled WHERE u.id = :id")
    void updateIsEnabledById(@Param("isEnabled") boolean isEnabled, @Param("id") Long id);
}
