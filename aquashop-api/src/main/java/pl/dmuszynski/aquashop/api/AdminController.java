package pl.dmuszynski.aquashop.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import pl.dmuszynski.aquashop.service.AdminService;
import pl.dmuszynski.aquashop.payload.UserDTO;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequiredArgsConstructor
@PreAuthorize(value = "hasRole('USER')")
@RequestMapping(value = "/admin-management/users")
public class AdminController {

    private final AdminService adminService;

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDetails, @PathVariable Long id) {
        final UserDTO updatedUserDto = this.adminService.updateUser(userDetails, id);
        return new ResponseEntity<>(updatedUserDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAllUserDto() {
        List<UserDTO> foundUserDtoList = this.adminService.findAllUserDto();

        if (!foundUserDtoList.isEmpty())
            return new ResponseEntity<>(foundUserDtoList, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
