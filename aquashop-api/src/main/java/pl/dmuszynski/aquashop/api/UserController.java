package pl.dmuszynski.aquashop.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import pl.dmuszynski.aquashop.service.IAuthenticationService;
import pl.dmuszynski.aquashop.service.IRegistrationService;

import pl.dmuszynski.aquashop.service.IUserService;
import pl.dmuszynski.aquashop.model.User;

@RestController
@RequestMapping(value = "user-management/users")
public class UserController {

    private final IAuthenticationService authenticationService;
    private final IRegistrationService registrationService;
    private final IUserService userService;

    @Autowired
    public UserController(IAuthenticationService authenticationService, IRegistrationService registrationService,
                          IUserService userService)
    {
        this.authenticationService = authenticationService;
        this.registrationService = registrationService;
        this.userService = userService;
    }

    @PostMapping(value = "/register")
    public void register(@RequestBody User user) {
        registrationService.register(user.getEmail(), user.getPassword());
    }

    @GetMapping(value = "/token")
    public void signUp(@RequestParam String value) {
        registrationService.signUp(value);
    }

    @PatchMapping(value = "/email")
    public void changeEmail(@RequestBody User user) {
        this.userService.changeEmail(user.getEmail(), user.getId());
    }

    @PatchMapping(value = "/password")
    public void changePassword(@RequestBody User user) {
        this.userService.changePassword(user.getPassword(), user.getId());
    }

    @DeleteMapping(value = "/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        userService.deleteById(id);
    }
}
