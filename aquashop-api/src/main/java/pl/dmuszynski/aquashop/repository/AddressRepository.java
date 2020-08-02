package pl.dmuszynski.aquashop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.dmuszynski.aquashop.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    @Modifying @Query("UPDATE Address a SET a.country = :country WHERE a.id = :id")
    void updateCountryById(@Param("country") String country, @Param("id") Long id);

    @Modifying @Query("UPDATE Address a SET a.location = :location WHERE a.id = :id")
    void updateLocationById(@Param("location") String location, @Param("id") Long id);

    @Modifying @Query("UPDATE Address a SET a.zipCode = :zipCode WHERE a.id = :id")
    void updateZipCodeById(@Param("zipCode") String zipCode, @Param("id") Long id);

    @Modifying @Query("UPDATE Address a SET a.street = :street WHERE a.id = :id")
    void updateStreetById(@Param("street") String street, @Param("id") Long id);
}
