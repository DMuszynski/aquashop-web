package pl.dmuszynski.aquashop.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import pl.dmuszynski.aquashop.entity.Token;
import pl.dmuszynski.aquashop.service.AuthenticationService;
import pl.dmuszynski.aquashop.service.RegistrationService;
import pl.dmuszynski.aquashop.service.TokenService;
import pl.dmuszynski.aquashop.service.UserService;
import pl.dmuszynski.aquashop.entity.User;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private AuthenticationService authenticationService;
    private RegistrationService registrationService;
    private TokenService tokenService;
    private UserService userService;

    @Autowired
    public UserController(AuthenticationService authenticationService, RegistrationService registrationService,
                          TokenService tokenService, UserService userService)
    {
        this.authenticationService = authenticationService;
        this.registrationService = registrationService;
        this.tokenService = tokenService;
        this.userService = userService;
    }

    @PostMapping(value = "/register")
    public void register(@RequestBody User user) {
        registrationService.register(user.getEmail(), user.getPassword());
    }

    @GetMapping(value = "/token")
    public void signUp(@RequestParam String value) {
        Token tokenByValue = tokenService.findTokenByValue(value);
        User user = tokenByValue.getUser();
        userService.updateUserIsEnabledById(user.getId(), true);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        userService.deleteById(id);
    }

    @GetMapping(value = "/findAll")
    public Iterable<User> findAll() {
        return userService.findAll();
    }
}
