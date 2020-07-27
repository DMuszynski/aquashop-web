package pl.dmuszynski.aquashop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.dmuszynski.aquashop.model.Address;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {
    
}
