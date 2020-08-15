package pl.dmuszynski.aquashop.service.implementation;

import pl.dmuszynski.aquashop.exception.UserDuplicatePasswordException;
import pl.dmuszynski.aquashop.exception.UserEmailAlreadyExistException;
import pl.dmuszynski.aquashop.exception.UserNotFoundException;
import pl.dmuszynski.aquashop.repository.UserRepository;
import pl.dmuszynski.aquashop.service.UserProfileService;
import pl.dmuszynski.aquashop.model.User;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service(value = "userProfileService")
public class UserProfileServiceImpl implements UserProfileService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserProfileServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public User changePassword(String password, Long id) {
        final User user = this.userRepository.findById(id)
            .orElseThrow(UserNotFoundException::new);
        validateUserDuplicatePassword(password, user.getPassword());

        this.userRepository.updatePasswordById(passwordEncoder.encode(password), id);
        return user;
    }

    @Override
    public User changeEmail(String email, Long id) {
        final User user = this.userRepository.findById(id)
            .orElseThrow(UserNotFoundException::new);
        validateUserEmailAlreadyExist(email);

        this.userRepository.updateEmailById(email, id);
        return user;
    }

    @Override
    public void deleteById(Long id) {
        this.userRepository.deleteById(id);
    }

    private void validateUserEmailAlreadyExist(String email) {
        if (this.userRepository.findByEmail(email).isPresent())
            throw new UserEmailAlreadyExistException(email);
    }

    private void validateUserDuplicatePassword(String newPassword, String oldPassword) {
        if (this.passwordEncoder.matches(newPassword, oldPassword))
            throw new UserDuplicatePasswordException();
    }
}