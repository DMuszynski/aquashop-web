package pl.dmuszynski.aquashop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dmuszynski.aquashop.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
