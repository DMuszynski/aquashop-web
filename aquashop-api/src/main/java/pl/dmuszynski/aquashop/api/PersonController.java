package pl.dmuszynski.aquashop.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import pl.dmuszynski.aquashop.service.PersonService;
import pl.dmuszynski.aquashop.model.Person;

import java.time.LocalDate;

@RestController
@PreAuthorize(value = "hasRole('USER')")
@RequestMapping(value = "user-management/users/{id}/person-management/persons")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public ResponseEntity<Person> addUserPerson(@RequestBody Person person, @PathVariable(value = "id") Long userId) {
        final Person createdPerson = this.personService.addUserPerson(person, userId);
        return new ResponseEntity<>(createdPerson, HttpStatus.CREATED);
    }

    @PatchMapping(value = "/{id}/name")
    public ResponseEntity<Person> updateNameById(@RequestBody String name, @PathVariable Long id) {
        final Person updatedPerson = this.personService.updateNameById(name, id);
        return new ResponseEntity<>(updatedPerson, HttpStatus.OK);
    }

    @PatchMapping(value = "/{id}/surname")
    public ResponseEntity<Person> updateSurnameById(@RequestBody String surname, @PathVariable Long id) {
        final Person updatedPerson = this.personService.updateSurnameById(surname, id);
        return new ResponseEntity<>(updatedPerson, HttpStatus.OK);
    }

    @PatchMapping(value = "/{id}/phone-number")
    public ResponseEntity<Person> updatePhoneNumberById(@RequestBody String phoneNumber, @PathVariable Long id) {
        final Person updatedPerson = this.personService.updatePhoneNumberById(phoneNumber, id);
        return new ResponseEntity<>(updatedPerson, HttpStatus.OK);
    }

    @PatchMapping(value = "/{id}/date-of-birth")
    public ResponseEntity<Person> updateDateOfBirthById(@RequestBody LocalDate dateOfBirth, @PathVariable Long id) {
        final Person updatedPerson = this.personService.updateDateOfBirthById(dateOfBirth, id);
        return new ResponseEntity<>(updatedPerson, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> deleteByUserId(@PathVariable Long id) {
        this.personService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
