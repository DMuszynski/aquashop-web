package pl.dmuszynski.aquashop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.dmuszynski.aquashop.payload.ProductDTO;
import pl.dmuszynski.aquashop.model.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT new pl.dmuszynski.aquashop.payload.ProductDTO(p.id, p.name, p.price) " +
        "FROM Product p " +
        "WHERE p.id =:id")
    Optional<ProductDTO> findProductDtoById(@Param("id") Long id);

    @Query(value = "SELECT distinct new pl.dmuszynski.aquashop.payload.ProductDTO(p.id, p.name, p.price) " +
        "FROM Product p")
    List<ProductDTO> findAllProductDto();
}
