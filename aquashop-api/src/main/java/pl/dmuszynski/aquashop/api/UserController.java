package pl.dmuszynski.aquashop.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import pl.dmuszynski.aquashop.service.AuthenticationService;
import pl.dmuszynski.aquashop.service.IRegistrationService;

import pl.dmuszynski.aquashop.service.IUserService;
import pl.dmuszynski.aquashop.model.User;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private final AuthenticationService authenticationService;
    private final IRegistrationService registrationService;
    private final IUserService userService;

    @Autowired
    public UserController(AuthenticationService authenticationService, IRegistrationService registrationService,
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

    @PatchMapping(value = "/token")
    public void signUp(@RequestParam String value) {
        registrationService.signUp(value);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        userService.deleteById(id);
    }
}
