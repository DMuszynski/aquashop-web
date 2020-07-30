package pl.dmuszynski.aquashop.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.dmuszynski.aquashop.model.RoleType;
import pl.dmuszynski.aquashop.model.User;
import pl.dmuszynski.aquashop.service.AdminService;

@RestController
@RequestMapping(value = "/admin-management/users")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public Iterable<User> findAll() {
        return this.adminService.findAll();
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
