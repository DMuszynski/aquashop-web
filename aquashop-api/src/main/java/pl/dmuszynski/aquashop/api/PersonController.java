package pl.dmuszynski.aquashop.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import pl.dmuszynski.aquashop.service.PersonService;
import pl.dmuszynski.aquashop.model.Person;

@RestController
@PreAuthorize(value = "hasRole('USER')")
@RequestMapping(value = "user-management/users/{userId}/person-management/persons")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public ResponseEntity<Person> addUserPerson(@RequestBody Person person, @PathVariable Long userId) {
        final Person createdPerson = this.personService.addUserPerson(person, userId);
        return new ResponseEntity<>(createdPerson, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Person> updatePerson(@RequestBody Person person, @PathVariable Long id) {
        final Person updatedPerson = this.personService.updatePerson(person, id);
        return new ResponseEntity<>(updatedPerson, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Person> findById(@PathVariable Long id) {
        final Person foundPerson = this.personService.findById(id);
        return new ResponseEntity<>(foundPerson, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> deleteByUserId(@PathVariable Long id) {
        this.personService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
