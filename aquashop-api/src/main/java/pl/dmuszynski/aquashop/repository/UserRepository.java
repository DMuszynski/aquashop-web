package pl.dmuszynski.aquashop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dmuszynski.aquashop.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
