package pl.dmuszynski.aquashop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.dmuszynski.aquashop.exception.CustomerExistException;
import pl.dmuszynski.aquashop.repository.RoleRepository;
import pl.dmuszynski.aquashop.repository.UserRepository;
import pl.dmuszynski.aquashop.model.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email)
            .orElseThrow(() -> new CustomerExistException(email));
    }

    public void deleteById(Long id) {
        this.userRepository.deleteById(id);
    }

    public User save(User user) {
        return this.userRepository.save(user);
    }

    public void updateUserIsEnabledById(Long id, boolean isEnabled) {
        this.userRepository.updateUserIsEnabledById(id, isEnabled);
    }

    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> getUsers() {
        List<User> list = new ArrayList<>();
        userRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }
}
