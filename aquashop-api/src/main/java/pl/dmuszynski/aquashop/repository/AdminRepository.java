package pl.dmuszynski.aquashop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.dmuszynski.aquashop.model.User;

@Repository
public interface AdminRepository extends JpaRepository<User, Long> {

    @Modifying @Query(value = "UPDATE User u SET u.isEnabled = :isEnabled WHERE u.id = :id")
    void updateUserIsEnabledById(@Param("isEnabled") boolean isEnabled, @Param("id") Long id);

    @Modifying @Query(value = "UPDATE user_role r SET r.role_id = :roleId WHERE r.user_id = :userId", nativeQuery = true)
    void updateUserRole(@Param("roleId") Long roleId, @Param("userId") Long userId);
}
