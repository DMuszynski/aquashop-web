package pl.dmuszynski.aquashop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.dmuszynski.aquashop.entity.Token;

@Repository
public interface TokenRepository extends CrudRepository<Token, Long> {

}
