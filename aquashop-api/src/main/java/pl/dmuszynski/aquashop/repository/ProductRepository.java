package pl.dmuszynski.aquashop.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository {

    @Modifying @Query(value = "UPDATE Product p SET p.prize = :prize WHERE p.id = :id")
    void updatePrizeById(@Param("prize") float prize, @Param("id") Long id);

    @Modifying @Query(value = "UPDATE Product p SET p.name = :name WHERE p.id = :id")
    void updateNameById(@Param("name") String name, @Param("id") Long id);
}
