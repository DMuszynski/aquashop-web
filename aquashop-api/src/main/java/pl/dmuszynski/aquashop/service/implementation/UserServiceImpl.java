package pl.dmuszynski.aquashop.service.implementation;

import pl.dmuszynski.aquashop.exception.DuplicatePasswordException;
import pl.dmuszynski.aquashop.exception.UniqueEmailException;
import pl.dmuszynski.aquashop.repository.UserRepository;
import pl.dmuszynski.aquashop.service.UserService;
import pl.dmuszynski.aquashop.payload.UserDTO;
import pl.dmuszynski.aquashop.model.User;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service(value = "userProfileService")
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository, ModelMapper modelMapper) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public User save(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public UserDTO changePassword(String password, Long id) {
        final User foundUser = this.findUserById(id);
        validateUserDuplicatePassword(password, foundUser.getPassword());
        foundUser.setPassword(password);

        this.userRepository.updatePasswordById(passwordEncoder.encode(password), id);
        return this.modelMapper.map(foundUser, UserDTO.class);
    }

    @Override
    public UserDTO changeEmail(String email, Long id) {
        final User foundUser = this.findUserById(id);
        validateUserEmailAlreadyExist(email);
        foundUser.setEmail(email);

        this.userRepository.updateEmailById(email, id);
        return this.modelMapper.map(foundUser, UserDTO.class);
    }

    @Override
    public UserDTO findUserDtoById(Long id) {
        final User foundUser = this.findUserById(id);
        return this.modelMapper.map(foundUser, UserDTO.class);
    }

    @Override
    public List<UserDTO> findAllUserDto() {
        final List<User> foundUserList = this.userRepository.findAll();
        return foundUserList.stream()
            .map(user -> this.modelMapper.map(user, UserDTO.class))
            .collect(Collectors.toList());
    }

    @Override
    public User findUserById(Long id) throws ResourceNotFoundException {
        return this.userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User not found for this id: " + id));
    }

    @Override
    public void deleteById(Long id) {
        this.userRepository.deleteById(id);
    }

    private void validateUserEmailAlreadyExist(String email) throws UniqueEmailException {
        if (this.userRepository.findByEmail(email).isPresent())
            throw new UniqueEmailException("The user with given address e-mail " + email + " is already exist");
    }

    private void validateUserDuplicatePassword(String newPassword, String oldPassword) throws DuplicatePasswordException {
        if (this.passwordEncoder.matches(newPassword, oldPassword))
            throw new DuplicatePasswordException();
    }
}