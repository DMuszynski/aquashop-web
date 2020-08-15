package pl.dmuszynski.aquashop.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import pl.dmuszynski.aquashop.service.UserService;
import pl.dmuszynski.aquashop.model.User;

@RestController
@PreAuthorize(value = "hasRole('USER')")
@RequestMapping(value = "user-management/users/{id}")
public class UserProfileController {

    private final UserService userService;

    @Autowired
    public UserProfileController(UserService userService) {
        this.userService = userService;
    }

    @PatchMapping(value = "/email")
    public ResponseEntity<User> changeEmail(@RequestBody User user, @PathVariable Long id) {
        final User updatedUser = this.userService.changeEmail(user.getEmail(), id);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @PatchMapping(value = "/password")
    public ResponseEntity<User> changePassword(@RequestBody User user, @PathVariable Long id) {
        final User updatedUser = this.userService.changePassword(user.getPassword(), id);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Long id) {
        this.userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
