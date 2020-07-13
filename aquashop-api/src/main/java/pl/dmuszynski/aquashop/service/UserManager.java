package pl.dmuszynski.aquashop.service;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.dmuszynski.aquashop.entity.User;
import pl.dmuszynski.aquashop.repository.UserRepository;

import java.time.LocalDate;

@Service
public class UserManager {

    private UserRepository userRepository;

    public UserManager(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {
//        User savedUser = new User.UserBuilder()
//            .email(user.getEmail())
//            .password(user.getPassword())
//            .created(LocalDate.now())
//            .build();

        return userRepository.save(user);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fillDb(){
        User user = new User.UserBuilder("email112", "password").build();
        save(user);
    }
}
