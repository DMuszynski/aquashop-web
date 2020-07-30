package pl.dmuszynski.aquashop.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.dmuszynski.aquashop.service.PersonService;
import pl.dmuszynski.aquashop.model.Person;

@RestController
@RequestMapping(value = "user-management/users/{id}/person-management/persons")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public void addUserPerson(@RequestBody Person person, @PathVariable("id") Long userId) {
        this.personService.addUserPerson(person, userId);
    }

    @PatchMapping(value = "/{id}/name")
    public void updateNameById(@RequestBody Person person, @PathVariable Long id) {
        this.personService.updateNameById(person.getName(), id);
    }

    @PatchMapping(value = "/{id}/surname")
    public void updateSurnameById(@RequestBody Person person, @PathVariable Long id) {
        this.personService.updateSurnameById(person.getSurname(), id);
    }

    @PatchMapping(value = "/{id}/phone-number")
    public void updatePhoneNumberById(@RequestBody Person person, @PathVariable Long id) {
        this.personService.updatePhoneNumberById(person.getPhoneNumber(), id);
    }

    @PatchMapping(value = "/{id}/date-of-birth")
    public void updateDateOfBirthById(@RequestBody Person person, @PathVariable Long id) {
        this.personService.updateDateOfBirthById(person.getDateOfBirth(), id);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteByUserId(@PathVariable Long id) {
        this.personService.deleteById(id);
    }
}
