package pl.dmuszynski.aquashop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.dmuszynski.aquashop.entity.User;
import pl.dmuszynski.aquashop.repository.RoleRepository;
import pl.dmuszynski.aquashop.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserManager {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public UserManager(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public void deleteById(Long id) {
        this.userRepository.deleteById(id);
    }

    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> getUsers() {
        List<User> list = new ArrayList<>();
        userRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

//    @EventListener(ApplicationReadyEvent.class)
//    public void fillDb(){
//        Role r1 = new Role();
//        Role r2 = new Role();
//        r1.setName("ROLE_USER");
//        r2.setName("ROLE_ADMIN");
//
//        roleRepository.save(r1);
//        roleRepository.save(r2);
//        this.userRepository.delete((this.userRepository.findById(2L)).get());

//        register("email1122", "password");
//    }
}
