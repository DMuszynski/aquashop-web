package pl.dmuszynski.aquashop.service.implementation;

import pl.dmuszynski.aquashop.exception.UserDuplicatePasswordException;
import pl.dmuszynski.aquashop.exception.UserEmailAlreadyExistException;
import pl.dmuszynski.aquashop.repository.UserRepository;
import pl.dmuszynski.aquashop.service.UserService;
import pl.dmuszynski.aquashop.model.User;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service(value = "userProfileService")
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public User changePassword(String password, Long id) {
        final User user = this.findById(id);
        validateUserDuplicatePassword(password, user.getPassword());

        this.userRepository.updatePasswordById(passwordEncoder.encode(password), id);
        return user;
    }

    @Override
    public User changeEmail(String email, Long id) {
        final User user = this.findById(id);
        validateUserEmailAlreadyExist(email);

        this.userRepository.updateEmailById(email, id);
        return user;
    }

    @Override
    public User findById(Long id) throws ResourceNotFoundException {
        return this.userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User not found for this id: " + id));
    }

    @Override
    public void deleteById(Long id) {
        this.userRepository.deleteById(id);
    }

    private void validateUserEmailAlreadyExist(String email) throws UserEmailAlreadyExistException {
        if (this.userRepository.findByEmail(email).isPresent())
            throw new UserEmailAlreadyExistException("The user with given address e-mail " + email + " is already exist");
    }

    private void validateUserDuplicatePassword(String newPassword, String oldPassword) throws UserDuplicatePasswordException {
        if (this.passwordEncoder.matches(newPassword, oldPassword))
            throw new UserDuplicatePasswordException();
    }
}