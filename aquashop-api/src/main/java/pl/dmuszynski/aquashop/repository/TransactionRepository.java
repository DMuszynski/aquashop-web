package pl.dmuszynski.aquashop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dmuszynski.aquashop.model.Transaction;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllByUserId(Long userId);
}
