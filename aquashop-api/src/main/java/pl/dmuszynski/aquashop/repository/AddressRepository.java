package pl.dmuszynski.aquashop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dmuszynski.aquashop.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
