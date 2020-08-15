package pl.dmuszynski.aquashop.service;

import pl.dmuszynski.aquashop.model.Person;

public interface PersonService {
    Person addUserPerson(Person person, Long userId);
    Person updatePerson(Person person, Long id);
    Person findById(Long id);
    void deleteById(Long id);
}
