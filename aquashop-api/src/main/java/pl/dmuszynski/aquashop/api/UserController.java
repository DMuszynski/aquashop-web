package pl.dmuszynski.aquashop.api;

import org.springframework.web.bind.annotation.RestController;
import pl.dmuszynski.aquashop.entity.User;
import pl.dmuszynski.aquashop.service.UserManager;

@RestController
public class UserController {

    private UserManager userManager;

    public UserController(UserManager userManager) {
        this.userManager = userManager;
    }

    public void register() {
        User user = new User.UserBuilder("email", "password").build();
        userManager.save(user);
    }
}
