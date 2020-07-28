package pl.dmuszynski.aquashop.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.dmuszynski.aquashop.service.implementation.AddressService;
import pl.dmuszynski.aquashop.model.Address;

@RestController
@RequestMapping("/address-management/addresses")
public class AddressController {

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping(value = "/user-management/users/{userId}/addresses")
    public void addUserAddress(@RequestBody Address address, @PathVariable Long userId) {
        addressService.addUserAddress(address, userId);
    }

    @GetMapping(value = "/user-management/users/{userId}/addresses")
    public Iterable<Address> findAllById(@PathVariable Long userId) {
        return addressService.findAllById(userId);
    }

    @PutMapping(value = "/user-management/users/{userId}/addresses/{addressId}")
    public void updateUserAddress(@RequestBody Address address, @PathVariable Long userId, @PathVariable Long addressId) {

    }

    @DeleteMapping(value = "/user-management/users/{userId}/addresses/{addressId}")
    public void deleteById(@PathVariable Long id) {
        addressService.deleteById(id);
    }
}
