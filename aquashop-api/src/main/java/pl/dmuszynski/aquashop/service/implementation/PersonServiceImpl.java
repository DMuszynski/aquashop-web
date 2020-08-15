package pl.dmuszynski.aquashop.service.implementation;

import pl.dmuszynski.aquashop.exception.PersonNotFoundException;
import pl.dmuszynski.aquashop.exception.UserNotFoundException;
import pl.dmuszynski.aquashop.repository.PersonRepository;
import pl.dmuszynski.aquashop.repository.UserRepository;
import pl.dmuszynski.aquashop.service.PersonService;
import pl.dmuszynski.aquashop.model.Person;
import pl.dmuszynski.aquashop.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Transactional
@Service(value = "personService")
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final UserRepository userRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository, UserRepository userRepository) {
        this.personRepository = personRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Person addUserPerson(Person person, Long userId) {
        final User user = this.userRepository.findById(userId)
            .orElseThrow(UserNotFoundException::new);

        return this.personRepository.save(
            new Person(user,
                person.getName(),
                person.getSurname(),
                person.getPhoneNumber(),
                person.getDateOfBirth()
            )
        );
    }

    @Override
    public Person updateDateOfBirthById(LocalDate dateOfBirth, Long id) {
        final Person person = this.personRepository.findById(id)
            .orElseThrow(PersonNotFoundException::new);
        person.setDateOfBirth(dateOfBirth);

        this.personRepository.updateDateOfBirthById(person.getDateOfBirth(), id);
        return person;
    }

    @Override
    public Person updatePhoneNumberById(String phoneNumber, Long id) {
        final Person person = this.personRepository.findById(id)
            .orElseThrow(PersonNotFoundException::new);
        person.setPhoneNumber(phoneNumber);

        this.personRepository.updatePhoneNumberById(person.getPhoneNumber(), id);
        return person;
    }

    @Override
    public Person updateSurnameById(String surname, Long id) {
        final Person person = this.personRepository.findById(id)
            .orElseThrow(PersonNotFoundException::new);
        person.setSurname(surname);

        this.personRepository.updateSurnameById(person.getSurname(), id);
        return person;
    }

    @Override
    public Person updateNameById(String name, Long id) {
        final Person person = this.personRepository.findById(id)
            .orElseThrow(PersonNotFoundException::new);
        person.setName(name);

        this.personRepository.updateNameById(person.getName(), id);
        return person;
    }

    @Override
    public void deleteById(Long id) {
        this.personRepository.deleteById(id);
    }
}
