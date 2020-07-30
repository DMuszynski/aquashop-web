package pl.dmuszynski.aquashop.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import pl.dmuszynski.aquashop.exception.UserNotFoundException;
import pl.dmuszynski.aquashop.model.Person;
import pl.dmuszynski.aquashop.model.User;
import pl.dmuszynski.aquashop.repository.PersonRepository;
import pl.dmuszynski.aquashop.repository.UserRepository;
import pl.dmuszynski.aquashop.service.PersonService;
import org.springframework.stereotype.Service;
import pl.dmuszynski.aquashop.service.UserService;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Service @Transactional
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final UserService userService;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository, UserService userService) {
        this.personRepository = personRepository;
        this.userService = userService;
    }

    @Override
    public Person add(Person person, Long userId) {
        final User user = userService.findById(userId);
        person.setUser(user);

        return this.personRepository.save(person);
    }

    @Override
    public void updateDateOfBirthById(LocalDate dateOfBirth, Long id) {
        this.personRepository.updateDateOfBirthById(dateOfBirth, id);
    }

    @Override
    public void updatePhoneNumberById(String phoneNumber, Long id) {
        this.personRepository.updatePhoneNumberById(phoneNumber, id);
    }

    @Override
    public void updateSurnameById(String surname, Long id) {
        this.personRepository.updateSurnameById(surname, id);
    }

    @Override
    public void updateNameById(String name, Long id) {
        this.personRepository.updateNameById(name, id);
    }

    @Override
    public void deleteById(Long id) {
        this.personRepository.deleteById(id);
    }
}
