package pl.dmuszynski.aquashop.service;

import pl.dmuszynski.aquashop.payload.UserDTO;

import java.util.List;

public interface AdminService {
    UserDTO updateUser(UserDTO userDetails, Long id);
    List<UserDTO> findAllUserDto();
}
