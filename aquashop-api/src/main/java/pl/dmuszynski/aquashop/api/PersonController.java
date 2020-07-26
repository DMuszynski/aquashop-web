package pl.dmuszynski.aquashop.api;

import org.springframework.web.bind.annotation.*;
import pl.dmuszynski.aquashop.model.Person;
import pl.dmuszynski.aquashop.model.User;

@RestController
@RequestMapping(value = "user-management/users")
public class PersonController {

    @PostMapping("{id}/person")
    public void save(@RequestBody Person person, @PathVariable("id") Long userId) {

    }

    @DeleteMapping("/{id}/person")
    public void deleteByUserId(@PathVariable("id") Long id) {

    }
}
