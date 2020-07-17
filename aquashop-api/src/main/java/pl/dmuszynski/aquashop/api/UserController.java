package pl.dmuszynski.aquashop.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.dmuszynski.aquashop.entity.User;
import pl.dmuszynski.aquashop.service.AuthorizationManager;
import pl.dmuszynski.aquashop.service.RegistrationManager;
import pl.dmuszynski.aquashop.service.UserManager;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private AuthorizationManager authorizationManager;
    private RegistrationManager registrationManager;
    private UserManager userManager;

    @Autowired
    public UserController(AuthorizationManager authorizationManager, RegistrationManager registrationManager,
                          UserManager userManager)
    {
        this.authorizationManager = authorizationManager;
        this.registrationManager = registrationManager;
        this.userManager = userManager;
    }

    @PostMapping(value = "/register")
    public void register(@RequestBody User user) {
        registrationManager.register(user.getEmail(), user.getPassword());
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        userManager.deleteById(id);
    }

    @GetMapping(value = "/findAll")
    public Iterable<User> findAll() {
        return userManager.findAll();
    }
}
