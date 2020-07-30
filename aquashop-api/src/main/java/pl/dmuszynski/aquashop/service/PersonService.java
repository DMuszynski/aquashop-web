package pl.dmuszynski.aquashop.service;

import pl.dmuszynski.aquashop.model.Person;

import java.time.LocalDate;

public interface PersonService {
    void addUserPerson(Person person, Long userId);
    void updateDateOfBirthById(LocalDate dateOfBirth, Long id);
    void updatePhoneNumberById(String phoneNumber, Long id);
    void updateSurnameById(String surname, Long id);
    void updateNameById(String name, Long id);
    void deleteById(Long id);
}
