package pl.dmuszynski.aquashop.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.dmuszynski.aquashop.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Modifying
    @Query("UPDATE User u SET u.isEnabled = :isEnabled WHERE u.id = :id")
    void updateUserIsEnabledById(@Param("id") Long id, @Param("isEnabled") boolean isEnabled);
}
