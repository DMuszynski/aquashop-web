package pl.dmuszynski.aquashop.api;

import pl.dmuszynski.aquashop.payload.request.SignupRequestDTO;
import pl.dmuszynski.aquashop.payload.request.LoginRequestDTO;
import pl.dmuszynski.aquashop.payload.response.JwtResponseDTO;
import pl.dmuszynski.aquashop.payload.UserDTO;
import pl.dmuszynski.aquashop.service.RegistrationService;
import pl.dmuszynski.aquashop.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth-management/")
public class AuthController {

    private final RegistrationService registrationService;
    private final AuthService authService;

    @Autowired
    public AuthController(RegistrationService registrationService, AuthService authService) {
        this.registrationService = registrationService;
        this.authService = authService;
    }

    @PostMapping(value = "/sign-in")
    public ResponseEntity<JwtResponseDTO> authenticateUser(@RequestBody @Valid LoginRequestDTO loginDetails) {
        final JwtResponseDTO authenticatedUserResponse = this.authService.authenticateUser(loginDetails);
        return new ResponseEntity<>(authenticatedUserResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/sign-up")
    public ResponseEntity<UserDTO> registerUser(@RequestBody @Valid SignupRequestDTO signupDetails) {
        final UserDTO registeredUserDto = this.registrationService.registerUser(signupDetails);
        return new ResponseEntity<>(registeredUserDto, HttpStatus.CREATED);
    }

    @GetMapping(value = "/token")
    public ResponseEntity<HttpStatus> activateUser(@RequestParam String value) {
        this.registrationService.activateAccountByUserToken(value);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
