package pl.dmuszynski.aquashop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.dmuszynski.aquashop.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}
