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

@RequiredArgsConstructor
@Service(value = "userProfileService")
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public UserDTO changePassword(String password, Long id) {
        final User foundUser = this.findUserById(id);
        new DuplicatePasswordValidator(passwordEncoder).validate(password, foundUser.getPassword());
        foundUser.setPassword(this.passwordEncoder.encode(password));

        this.userRepository.updatePasswordById(foundUser.getPassword(), foundUser.getId());
        return this.modelMapper.map(foundUser, UserDTO.class);
    }

    @Override
    @Transactional
    public UserDTO changeEmail(String email, Long id) {
        new UniqueEmailValidator(userRepository).validate(email);
        final User foundUser = this.findUserById(id);
        foundUser.setEmail(email);

        this.userRepository.updateEmailById(foundUser.getEmail(), foundUser.getId());
        return this.modelMapper.map(foundUser, UserDTO.class);
    }

    @Override
    public UserDTO findUserDtoById(Long id) throws ResourceNotFoundException {
        return this.userRepository.findUserDtoById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User not found for this id: " + id));
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