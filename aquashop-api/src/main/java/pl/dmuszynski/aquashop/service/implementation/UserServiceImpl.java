package pl.dmuszynski.aquashop.service.implementation;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.dmuszynski.aquashop.exception.UserEmailAlreadyExistException;
import pl.dmuszynski.aquashop.exception.UserSamePasswordException;
import pl.dmuszynski.aquashop.exception.UserNotFoundException;
import pl.dmuszynski.aquashop.repository.UserRepository;
import pl.dmuszynski.aquashop.service.UserService;
import pl.dmuszynski.aquashop.model.User;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public void changePassword(String password, Long id) {
        validateSamePassword(password, findById(id).getPassword());
        this.userRepository.updatePasswordById(passwordEncoder.encode(password), id);
    }

    @Transactional
    @Override
    public void changeEmail(String email, Long id) {
        validateUserEmailAlreadyExist(email);
        this.userRepository.updateEmailById(email, id);
    }

    @Override
    public void deleteById(Long id) {
        this.userRepository.deleteById(id);
    }

    @Override
    public User findById(Long id) {
        return this.userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException(id));
    }

    private void validateUserEmailAlreadyExist(String email) {
        if (this.userRepository.findByEmail(email).isPresent())
            throw new UserEmailAlreadyExistException(email);
    }

    private void validateSamePassword(String password, String oldPassword) {
        if (!this.passwordEncoder.matches(password, oldPassword)) {
            throw new UserSamePasswordException();
        }
    }
}