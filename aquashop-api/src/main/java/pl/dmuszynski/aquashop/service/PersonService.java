package pl.dmuszynski.aquashop.service;

import org.springframework.stereotype.Service;
import pl.dmuszynski.aquashop.model.Person;
import pl.dmuszynski.aquashop.repository.PersonRepository;

import java.time.LocalDate;

@Service
public class PersonService implements IPersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    @Override
    public void updateDateOfBirthById(LocalDate dateOfBirth, Long id) {
        personRepository.updateDateOfBirthById(dateOfBirth, id);
    }

    @Override
    public void updatePhoneNumberById(String phoneNumber, Long id) {
        personRepository.updatePhoneNumberById(phoneNumber, id);
    }

    @Override
    public void updateSurnameById(String surname, Long id) {
        personRepository.updateSurnameById(surname, id);
    }

    @Override
    public void updateNameById(String name, Long id) {
        personRepository.updateNameById(name, id);
    }

    @Override
    public void deleteById(Long id) {
        personRepository.deleteById(id);
    }
}
