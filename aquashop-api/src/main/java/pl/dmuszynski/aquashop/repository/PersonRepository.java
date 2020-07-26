package pl.dmuszynski.aquashop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.dmuszynski.aquashop.model.Person;

import java.time.LocalDate;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

    @Modifying
    @Query("UPDATE Person p SET p.dateOfBirth = :dateOfBirth WHERE p.id = :id")
    void updateDateOfBirthById(@Param("dateOfBirth") LocalDate dateOfBirth, @Param("id") Long id);

    @Modifying
    @Query("UPDATE Person p SET p.phoneNumber = :phoneNumber WHERE p.id = :id")
    void updatePhoneNumberById(@Param("phoneNumber") String phoneNumber, @Param("id") Long id);

    @Modifying
    @Query("UPDATE Person p SET p.surname = :surname WHERE p.id = :id")
    void updateSurnameById(@Param("surname") String surname, @Param("id") Long id);

    @Modifying
    @Query("UPDATE Person p SET p.name = :name WHERE p.id = :id")
    void updateNameById(@Param("name") String name, @Param("id") Long id);

    void deleteById(Long id);
}
