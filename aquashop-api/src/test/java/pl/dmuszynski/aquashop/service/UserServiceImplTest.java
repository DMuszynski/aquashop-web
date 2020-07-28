package pl.dmuszynski.aquashop.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import pl.dmuszynski.aquashop.model.User;
import pl.dmuszynski.aquashop.repository.RoleRepository;
import pl.dmuszynski.aquashop.repository.UserRepository;
import pl.dmuszynski.aquashop.service.implementation.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Spy
    private UserServiceImpl userServiceImpl;

    @Before
    public void init() {

    }

    @Test
    public void register() {

    }

    public List<User> prepareMockData() {
        return new ArrayList<>();
    }
}