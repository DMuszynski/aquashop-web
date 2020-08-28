package pl.dmuszynski.aquashop.validator;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import pl.dmuszynski.aquashop.exception.DuplicatePasswordException;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DuplicatePasswordValidator {

    private final PasswordEncoder passwordEncoder;

    public void validate(String newPassword, String oldPassword) throws DuplicatePasswordException {
        if (this.passwordEncoder.matches(newPassword, oldPassword))
            throw new DuplicatePasswordException();
    }
}