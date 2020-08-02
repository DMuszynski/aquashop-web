package pl.dmuszynski.aquashop.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dmuszynski.aquashop.service.RegistrationService;
import pl.dmuszynski.aquashop.service.UserService;
import pl.dmuszynski.aquashop.model.User;

@RestController
@RequestMapping(value = "user-management/users")
public class UserController {

    private final RegistrationService registrationService;
    private final UserService userService;

    @Autowired
    public UserController(RegistrationService registrationService, UserService userService) {
        this.registrationService = registrationService;
        this.userService = userService;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        final User registerUser = this.registrationService.register(user.getEmail(), user.getPassword());
        return new ResponseEntity<>(registerUser, HttpStatus.CREATED);
    }

    @GetMapping(value = "/token")
    public void signUp(@RequestParam String value) {
        this.registrationService.signUp(value);
    }

    @PatchMapping(value = "/{id}/email")
    public void changeEmail(@RequestBody User user, @PathVariable Long id) {
        this.userService.changeEmail(user.getEmail(), id);
    }

    @PatchMapping(value = "/{id}/password")
    public void changePassword(@RequestBody User user, @PathVariable Long id) {
        this.userService.changePassword(user.getPassword(), id);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Long id) {
        this.userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
