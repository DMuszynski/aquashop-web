package pl.dmuszynski.aquashop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.dmuszynski.aquashop.model.Address;
import pl.dmuszynski.aquashop.payload.AddressDTO;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    @Query(value = "SELECT new pl.dmuszynski.aquashop.payload.AddressDTO(a.id, a.country, a.location, a.zipCode, a.street) FROM Address a WHERE a.user.id = :id")
    List<AddressDTO> findAllAddressDtoByUserId(@Param("id") Long id);
}
