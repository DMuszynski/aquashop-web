package pl.dmuszynski.aquashop.service;

import pl.dmuszynski.aquashop.model.User;
import pl.dmuszynski.aquashop.payload.UserDTO;

public interface UserService {
    UserDTO changePassword(String password, Long id);
    UserDTO changeEmail(String email, Long id);
    UserDTO findUserDtoById(Long id);
    User findUserById(Long id);
    void deleteById(Long id);
}
