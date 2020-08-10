package pl.dmuszynski.aquashop.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import pl.dmuszynski.aquashop.payload.request.LoginRequest;
import pl.dmuszynski.aquashop.service.AuthService;
import pl.dmuszynski.aquashop.model.User;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth-management")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = "/sign-in")
    public ResponseEntity<?> authenticateUser(@RequestBody User user) {
        return ResponseEntity.ok(authService.authenticateUser(user));
    }

    @PostMapping(value = "/sign-up")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/token")
    public void activateUser(@RequestParam String value) {

    }
}
