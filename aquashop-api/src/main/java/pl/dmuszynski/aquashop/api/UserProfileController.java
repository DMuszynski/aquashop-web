package pl.dmuszynski.aquashop.api;

import pl.dmuszynski.aquashop.payload.request.PasswordRequestDTO;
import pl.dmuszynski.aquashop.payload.UserDTO;
import pl.dmuszynski.aquashop.service.UserService;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import javax.validation.Valid;

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
    public ResponseEntity<UserDTO> changeEmail(@RequestBody @Valid UserDTO userDetails, @PathVariable Long id) {
        final UserDTO updatedUser = this.userService.changeEmail(userDetails.getEmail(), id);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @PatchMapping(value = "/password")
    public ResponseEntity<UserDTO> changePassword(@RequestBody @Valid PasswordRequestDTO passwordDetails, @PathVariable Long id) {
        final UserDTO updatedUser = this.userService.changePassword(passwordDetails.getPassword(), id);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Long id) {
        this.userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
