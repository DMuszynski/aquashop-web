package pl.dmuszynski.aquashop.validator;

import pl.dmuszynski.aquashop.exception.UniqueUsernameException;
import pl.dmuszynski.aquashop.exception.UniqueEmailException;
import pl.dmuszynski.aquashop.repository.UserRepository;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UniqueEmailValidator {

    private final UserRepository userRepository;

    public void validate(String email) throws UniqueEmailException {
        if (this.userRepository.existsByEmail(email))
            throw new UniqueUsernameException("The user with given email " + email + " is already exist");
    }
}