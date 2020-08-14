package pl.dmuszynski.aquashop.service;

import pl.dmuszynski.aquashop.model.Person;

import java.time.LocalDate;

public interface PersonService {
    Person addUserPerson(Person person, Long userId);
    Person updateDateOfBirthById(LocalDate dateOfBirth, Long id);
    Person updatePhoneNumberById(String phoneNumber, Long id);
    Person updateSurnameById(String surname, Long id);
    Person updateNameById(String name, Long id);
    void deleteById(Long id);
}
