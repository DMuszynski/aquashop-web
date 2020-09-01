package pl.dmuszynski.aquashop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.dmuszynski.aquashop.payload.PersonDTO;
import pl.dmuszynski.aquashop.model.Person;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    @Query(value = "SELECT new pl.dmuszynski.aquashop.payload.PersonDTO(p.id, p.name, p.surname, p.phoneNumber, p.dateOfBirth) FROM Person p WHERE p.id = :id")
    Optional<PersonDTO> findPersonDtoById(@Param("id") Long id);

//    @Query(value = "DELETE FROM Person p WHERE p.id = :id")
//    void deleteById(@Param("id")  Long id);
}
