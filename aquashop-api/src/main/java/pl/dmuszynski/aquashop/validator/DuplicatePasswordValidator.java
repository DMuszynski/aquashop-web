package pl.dmuszynski.aquashop.validator;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.dmuszynski.aquashop.exception.DuplicatePasswordException;

import lombok.RequiredArgsConstructor;

@Component
public class DuplicatePasswordValidator {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DuplicatePasswordValidator(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public void validate(String newPassword, String oldPassword) throws DuplicatePasswordException {
        if (this.passwordEncoder.matches(newPassword, oldPassword))
            throw new DuplicatePasswordException();
    }
}