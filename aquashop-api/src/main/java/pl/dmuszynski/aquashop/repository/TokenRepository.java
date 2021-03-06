package pl.dmuszynski.aquashop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dmuszynski.aquashop.model.Token;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findByValue(String value);
    boolean existsTokenByValue(String value);
}
