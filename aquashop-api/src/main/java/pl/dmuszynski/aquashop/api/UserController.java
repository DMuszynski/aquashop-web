package pl.dmuszynski.aquashop.api;

import org.springframework.web.bind.annotation.*;
import pl.dmuszynski.aquashop.entity.User;
import pl.dmuszynski.aquashop.service.UserManager;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private UserManager userManager;

    public UserController(UserManager userManager) {
        this.userManager = userManager;
    }

    @PostMapping(value = "/register")
    public void register(@RequestBody User user) {
        userManager.register(user.getEmail(), user.getPassword());
    }
}
