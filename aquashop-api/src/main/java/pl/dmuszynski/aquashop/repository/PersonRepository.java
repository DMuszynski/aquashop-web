package pl.dmuszynski.aquashop.repository;

import org.springframework.data.repository.CrudRepository;
import pl.dmuszynski.aquashop.model.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {

}
