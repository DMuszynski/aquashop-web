package pl.dmuszynski.aquashop.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import pl.dmuszynski.aquashop.service.AdminService;
import pl.dmuszynski.aquashop.model.RoleType;
import pl.dmuszynski.aquashop.model.User;

import java.util.List;

@RestController
@PreAuthorize(value = "hasRole('ADMIN')")
@RequestMapping(value = "/admin-management/users")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PatchMapping(value = "/{id}/user-role")
    public ResponseEntity<User> updateUserRole(@RequestBody User user, @PathVariable Long id) {
        final User updatedUser = this.adminService.updateUserRole(RoleType.ROLE_ADMIN, id);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @PatchMapping(value = "{id}/is-enabled")
    public ResponseEntity<User> updateUserIsEnabledById(@RequestBody User user, @PathVariable Long id) {
        final User updatedUser = this.adminService.updateUserIsEnabledById(user.isEnabled(), id);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        List<User> users = this.adminService.findAll();

        if (!users.isEmpty())
            return new ResponseEntity<>(users, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
