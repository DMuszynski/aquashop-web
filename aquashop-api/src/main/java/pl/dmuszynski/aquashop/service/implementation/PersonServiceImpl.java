package pl.dmuszynski.aquashop.service.implementation;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import pl.dmuszynski.aquashop.repository.PersonRepository;
import pl.dmuszynski.aquashop.payload.PersonDTO;
import pl.dmuszynski.aquashop.service.PersonService;
import pl.dmuszynski.aquashop.service.UserService;
import pl.dmuszynski.aquashop.model.Person;
import pl.dmuszynski.aquashop.model.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service(value = "personService")
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Override
    public PersonDTO addUserPerson(PersonDTO personDetails, Long userId) {
        final User foundUser = this.userService.findUserById(userId);
        final Person savedPerson = this.personRepository
            .save(Person.builder()
                .user(foundUser)
                .name(personDetails.getName())
                .surname(personDetails.getSurname())
                .phoneNumber(personDetails.getPhoneNumber())
                .dateOfBirth(personDetails.getDateOfBirth())
                .build()
            );

        return this.modelMapper.map(savedPerson, PersonDTO.class);
    }

    @Override
    public PersonDTO updatePerson(PersonDTO personDetails, Long personId) {
        final Person foundPerson = this.findPersonById(personId);
        final Person updatedPerson = this.personRepository
            .save(Person.builder()
                .id(foundPerson.getId())
                .user(foundPerson.getUser())
                .name(personDetails.getName())
                .surname(personDetails.getSurname())
                .phoneNumber(personDetails.getPhoneNumber())
                .dateOfBirth(personDetails.getDateOfBirth())
                .build()
            );

        return this.modelMapper.map(updatedPerson, PersonDTO.class);
    }

    @Override
    public PersonDTO findPersonDtoById(Long id) throws ResourceNotFoundException {
        return this.personRepository.findPersonDtoById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Person not found for this id: " + id));
    }

    private Person findPersonById(Long id) throws ResourceNotFoundException {
        return this.personRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Person not found for this id: " + id));
    }

    @Override
    public void deleteById(Long id) {
        this.personRepository.deleteById(id);
    }
}
