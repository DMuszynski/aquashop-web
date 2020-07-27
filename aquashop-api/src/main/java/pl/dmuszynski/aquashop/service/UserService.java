package pl.dmuszynski.aquashop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import pl.dmuszynski.aquashop.exception.UserEmailAlreadyExistException;
import pl.dmuszynski.aquashop.exception.UserNotFoundException;
import pl.dmuszynski.aquashop.exception.UserSamePasswordException;
import pl.dmuszynski.aquashop.repository.UserRepository;
import pl.dmuszynski.aquashop.model.User;

@Service
public class UserService implements IUserService{

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public void changePassword(String password, Long id) {
        validatePassword(password, findById(id).getPassword());
        userRepository.updatePasswordById(passwordEncoder.encode(password), id);
    }

    @Override
    public void changeEmail(String email, Long id) {
        validateEmail(email);
        userRepository.updateEmailById(email, id);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public void deleteById(Long id) {
        this.userRepository.deleteById(id);
    }

    private void validatePassword(String newPassword, String oldPassword) {
        if (!passwordEncoder.matches(newPassword, oldPassword)) {
            throw new UserSamePasswordException();
        }
    }

    private void validateEmail(String email) {
        userRepository.findByEmail(email)
            .orElseThrow(() -> new UserEmailAlreadyExistException(email));
    }
}
