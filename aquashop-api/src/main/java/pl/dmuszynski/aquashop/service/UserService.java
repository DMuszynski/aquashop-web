package pl.dmuszynski.aquashop.service;

import pl.dmuszynski.aquashop.payload.UserDTO;
import pl.dmuszynski.aquashop.model.User;

import java.util.List;

public interface UserService {
    User save(User user);
    UserDTO changePassword(String password, Long id);
    UserDTO changeEmail(String email, Long id);
    UserDTO findUserDtoById(Long id);
    List<UserDTO> findAllUserDto();
    User findUserById(Long id);
    void deleteById(Long id);
}
