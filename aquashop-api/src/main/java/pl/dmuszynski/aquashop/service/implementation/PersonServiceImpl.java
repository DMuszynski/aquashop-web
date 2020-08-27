package pl.dmuszynski.aquashop.service.implementation;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import pl.dmuszynski.aquashop.repository.PersonRepository;
import pl.dmuszynski.aquashop.payload.PersonDTO;
import pl.dmuszynski.aquashop.service.PersonService;
import pl.dmuszynski.aquashop.service.UserService;
import pl.dmuszynski.aquashop.model.Person;
import pl.dmuszynski.aquashop.model.User;

import lombok.RequiredArgsConstructor;

@Service(value = "personService")
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository, UserService userService, ModelMapper modelMapper) {
        this.personRepository = personRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public PersonDTO addUserPerson(PersonDTO personDetails, Long userId) {
        final User foundUser = this.userService.findUserById(userId);
        final Person savedPerson = this.personRepository
            .save(new Person(foundUser, personDetails.getName(), personDetails.getSurname(),
                personDetails.getPhoneNumber(), personDetails.getDateOfBirth()));

        return this.modelMapper.map(savedPerson, PersonDTO.class);
    }

    @Override
    public PersonDTO updatePerson(PersonDTO personDetails, Long id) {
        final Person foundPerson = this.findPersonById(id);
        final Person updatedPerson = this.personRepository
            .save(new Person(foundPerson.getId(), foundPerson.getUser(), personDetails.getName(),
                personDetails.getSurname(), personDetails.getPhoneNumber(), personDetails.getDateOfBirth()));

        return this.modelMapper.map(updatedPerson, PersonDTO.class);
    }

    private Person findPersonById(Long id) throws ResourceNotFoundException {
        return this.personRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Person not found for this id: " + id));
    }

    @Override
    public PersonDTO findPersonDtoById(Long id) {
        final Person foundPerson = this.findPersonById(id);
        return this.modelMapper.map(foundPerson, PersonDTO.class);
    }

    @Override
    public void deleteById(Long id) {
        this.personRepository.deleteById(id);
    }
}
