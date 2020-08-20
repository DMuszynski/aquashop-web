package pl.dmuszynski.aquashop.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import pl.dmuszynski.aquashop.payload.PersonDTO;
import pl.dmuszynski.aquashop.service.PersonService;

import javax.validation.Valid;

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
    public ResponseEntity<PersonDTO> addUserPerson(@RequestBody @Valid PersonDTO personDetails, @PathVariable Long userId) {
        final PersonDTO createdPerson = this.personService.addUserPerson(personDetails, userId);
        return new ResponseEntity<>(createdPerson, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PersonDTO> updatePerson(@RequestBody @Valid PersonDTO personDetails, @PathVariable Long id) {
        final PersonDTO updatedPerson = this.personService.updatePerson(personDetails, id);
        return new ResponseEntity<>(updatedPerson, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PersonDTO> findById(@PathVariable Long id) {
        final PersonDTO foundPerson = this.personService.findById(id);
        return new ResponseEntity<>(foundPerson, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> deleteByUserId(@PathVariable Long id) {
        this.personService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
