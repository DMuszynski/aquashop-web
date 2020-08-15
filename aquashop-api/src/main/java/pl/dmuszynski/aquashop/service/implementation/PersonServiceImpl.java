package pl.dmuszynski.aquashop.service.implementation;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.dmuszynski.aquashop.repository.PersonRepository;
import pl.dmuszynski.aquashop.service.PersonService;
import pl.dmuszynski.aquashop.service.UserService;
import pl.dmuszynski.aquashop.model.Person;
import pl.dmuszynski.aquashop.model.User;

@Service(value = "personService")
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final UserService userService;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository, UserService userService) {
        this.personRepository = personRepository;
        this.userService = userService;
    }

    @Override
    public Person addUserPerson(Person person, Long userId) {
        final User user = this.userService.findById(userId);
        return this.personRepository
            .save(new Person(user, person.getName(),
                person.getSurname(), person.getPhoneNumber(), person.getDateOfBirth()));
    }

    @Override
    public Person updatePerson(Person personDetails, Long id) {
        final Person person = this.findById(id);
        return this.personRepository
            .save(new Person(person.getUser(), person.getId(), personDetails.getName(),
                personDetails.getSurname(), personDetails.getPhoneNumber(), personDetails.getDateOfBirth()));
    }

    @Override
    public Person findById(Long id) throws ResourceNotFoundException {
        return this.personRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Person not found for this id: " + id));
    }

    @Override
    public void deleteById(Long id) {
        this.personRepository.deleteById(id);
    }
}
