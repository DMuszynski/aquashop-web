package pl.dmuszynski.aquashop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    public void changePassword(User user) {
        final User foundUser = findById(user.getId());


    }
    @Override
    public void deleteById(Long id) {
        this.userRepository.deleteById(id);
    }

    private void validatePassword(String oldPassword, String newPassword) {
        if (passwordEncoder.matches(oldPassword, newPassword)) {
            System.out.println("TO samo");
        } else {
            System.out.println("dobre hasło");
        }
    }
}
