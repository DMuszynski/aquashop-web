package pl.dmuszynski.aquashop.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import pl.dmuszynski.aquashop.payload.response.JwtResponse;
import pl.dmuszynski.aquashop.service.RegistrationService;
import pl.dmuszynski.aquashop.service.AuthService;
import pl.dmuszynski.aquashop.model.User;

@RestController
@RequestMapping("/auth-management")
public class AuthController {

    private final RegistrationService registrationService;
    private final AuthService authService;

    @Autowired
    public AuthController(RegistrationService registrationService, AuthService authService) {
        this.registrationService = registrationService;
        this.authService = authService;
    }

    @PostMapping(value = "/sign-in")
    public ResponseEntity<JwtResponse> authenticateUser(@RequestBody User user) {
        final JwtResponse authenticatedUserResponse = this.authService.authenticateUser(user);
        return new ResponseEntity<>(authenticatedUserResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/sign-up")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        final User registeredUser = this.registrationService.register(user.getEmail(), user.getPassword());
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    @GetMapping(value = "/token")
    public ResponseEntity<HttpStatus> activateUser(@RequestParam String value) {
        this.registrationService.activateAccountByUserToken(value);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
