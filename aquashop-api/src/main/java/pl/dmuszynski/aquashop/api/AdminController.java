package pl.dmuszynski.aquashop.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import pl.dmuszynski.aquashop.model.RoleType;
import pl.dmuszynski.aquashop.model.User;
import pl.dmuszynski.aquashop.service.AdminService;

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

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        List<User> users = this.adminService.findAll();

        if (!users.isEmpty())
            return new ResponseEntity<>(users, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(value = "/{id}/user-role")
    public void updateUserRole(@RequestBody User user, @PathVariable Long id) {
        this.adminService.updateUserRole(RoleType.ROLE_ADMIN, id);
    }

    @PatchMapping(value = "{id}/is-enabled")
    public void updateUserIsEnabledById(@RequestBody User user, @PathVariable Long id) {
        this.adminService.updateUserIsEnabledById(user.isEnabled(), id);
    }
}
