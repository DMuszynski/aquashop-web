package pl.dmuszynski.aquashop.validator;

import pl.dmuszynski.aquashop.exception.UniqueUsernameException;
import pl.dmuszynski.aquashop.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EnabledValidator {

    private final UserRepository userRepository;

    @Autowired
    public EnabledValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void validate(String username) throws UniqueUsernameException {
        if (this.userRepository.findByUsername(username).isPresent())
            throw new UniqueUsernameException("The user with given username " + username + " is already exist");
    }
}