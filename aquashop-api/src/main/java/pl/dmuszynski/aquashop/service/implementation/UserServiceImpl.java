package pl.dmuszynski.aquashop.service.implementation;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import pl.dmuszynski.aquashop.validator.DuplicatePasswordValidator;
import pl.dmuszynski.aquashop.validator.UniqueEmailValidator;
import pl.dmuszynski.aquashop.repository.UserRepository;
import pl.dmuszynski.aquashop.service.UserService;
import pl.dmuszynski.aquashop.payload.UserDTO;
import pl.dmuszynski.aquashop.model.User;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Service(value = "userProfileService")
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public User save(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public UserDTO changePassword(String password, Long id) {
        final User foundUser = this.findUserById(id);
        new DuplicatePasswordValidator(passwordEncoder).validate(password, foundUser.getPassword());

        this.userRepository.updatePasswordById(passwordEncoder.encode(password), id);
        return this.modelMapper.map(foundUser, UserDTO.class);
    }

    @Override
    public UserDTO changeEmail(String email, Long id) {
        new UniqueEmailValidator(userRepository).validate(email);
        final User foundUser = this.findUserById(id);
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
}