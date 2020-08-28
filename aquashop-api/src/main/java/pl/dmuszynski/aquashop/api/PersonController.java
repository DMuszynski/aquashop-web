package pl.dmuszynski.aquashop.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import pl.dmuszynski.aquashop.service.PersonService;
import pl.dmuszynski.aquashop.payload.PersonDTO;

import lombok.RequiredArgsConstructor;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@PreAuthorize(value = "hasRole('USER')")
@RequestMapping(value = "user-management/users/{userId}/person-management/persons")
public class PersonController {

    private final PersonService personService;

    @PostMapping
    public ResponseEntity<PersonDTO> addUserPerson(@RequestBody @Valid PersonDTO personDetails, @PathVariable Long userId) {
        final PersonDTO createdPersonDto = this.personService.addUserPerson(personDetails, userId);
        return new ResponseEntity<>(createdPersonDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PersonDTO> updatePerson(@RequestBody @Valid PersonDTO personDetails, @PathVariable Long id) {
        final PersonDTO updatedPersonDto = this.personService.updatePerson(personDetails, id);
        return new ResponseEntity<>(updatedPersonDto, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PersonDTO> findPersonDtoById(@PathVariable Long id) {
        final PersonDTO foundPersonDto = this.personService.findPersonDtoById(id);
        return new ResponseEntity<>(foundPersonDto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Long id) {
        this.personService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
