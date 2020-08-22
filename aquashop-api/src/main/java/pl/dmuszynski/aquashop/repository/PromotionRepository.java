package pl.dmuszynski.aquashop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dmuszynski.aquashop.model.Promotion;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {

}
