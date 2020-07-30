package pl.dmuszynski.aquashop.api;

import org.springframework.beans.factory.annotation.Autowired;
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
    public void register(@RequestBody User user) {
        this.registrationService.register(user.getEmail(), user.getPassword());
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
    public void deleteById(@PathVariable Long id) {
        this.userService.deleteById(id);
    }
}
