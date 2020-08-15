package pl.dmuszynski.aquashop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.dmuszynski.aquashop.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
