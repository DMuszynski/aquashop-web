package pl.dmuszynski.aquashop.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import pl.dmuszynski.aquashop.entity.User;
import pl.dmuszynski.aquashop.repository.RoleRepository;
import pl.dmuszynski.aquashop.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserManagerTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Spy
    private UserManager userManager;

    @Before
    public void init() {

    }

    @Test
    public void register() {

    }

    public List<User> prepareMockData() {

        System.out.println("DUPA");

        User u1 = userManager.register("a", "b");
        User u2 = userManager.register("c", "d");
        User u3 = userManager.register("e", "f");

        List<User> users = new ArrayList<>();
        userRepository.findAll().iterator().forEachRemaining(users::add);
        return users;
    }
}