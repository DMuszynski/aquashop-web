package pl.dmuszynski.aquashop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.dmuszynski.aquashop.repository.UserRepository;
import pl.dmuszynski.aquashop.model.User;

@Service
public class UserService implements IUserService{

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        this.userRepository.deleteById(id);
    }
}
