package pl.dmuszynski.aquashop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.dmuszynski.aquashop.exception.UserEmailAlreadyExistException;
import pl.dmuszynski.aquashop.repository.RoleRepository;
import pl.dmuszynski.aquashop.repository.UserRepository;
import pl.dmuszynski.aquashop.model.User;

@Service
public class UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public User save(User user) {
        return this.userRepository.save(user);
    }

    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email)
            .orElseThrow(() -> new UserEmailAlreadyExistException(email));
    }

    public void updateIsEnabledById(boolean isEnabled, Long id) {
        this.userRepository.updateIsEnabledById(isEnabled, id);
    }

    public void deleteById(Long id) {
        this.userRepository.deleteById(id);
    }
}
