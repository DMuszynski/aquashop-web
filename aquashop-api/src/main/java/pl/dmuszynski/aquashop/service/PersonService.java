package pl.dmuszynski.aquashop.service;

import java.time.LocalDate;

public interface PersonService {

    void updateDateOfBirthById(LocalDate dateOfBirth, Long id);
    void updatePhoneNumberById(String phoneNumber, Long id);
    void updateSurnameById(String surname, Long id);
    void updateNameById(String name, Long id);

    void deleteById(Long id);
}
