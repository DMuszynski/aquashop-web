package pl.dmuszynski.aquashop.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import pl.dmuszynski.aquashop.service.AdminService;
import pl.dmuszynski.aquashop.payload.UserDTO;

import java.util.List;

@RestController
@PreAuthorize(value = "hasRole('USER')")
@RequestMapping(value = "/admin-management/users")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDetails, @PathVariable Long id) {
        final UserDTO updatedUser = this.adminService.updateUser(userDetails, id);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        List<UserDTO> users = this.adminService.findAll();

        if (!users.isEmpty())
            return new ResponseEntity<>(users, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
