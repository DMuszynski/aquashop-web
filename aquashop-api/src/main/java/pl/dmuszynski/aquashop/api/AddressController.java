package pl.dmuszynski.aquashop.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import pl.dmuszynski.aquashop.service.AddressService;
import pl.dmuszynski.aquashop.model.Address;

@RestController
@PreAuthorize(value = "hasRole('USER')")
@RequestMapping(value = "user-management/users/{userId}/address-management/addresses")
public class AddressController {

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping
    public ResponseEntity<Address> addUserAddress(@RequestBody Address address, @PathVariable Long userId) {
        final Address createdAddress = this.addressService.addUserAddress(address, userId);
        return new ResponseEntity<>(createdAddress, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Address> updateAddress(@RequestBody Address address, @PathVariable Long id) {
        final Address updatedAddress = this.addressService.updateAddress(address, id);
        return new ResponseEntity<>(updatedAddress, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Address> findById(@PathVariable Long id) {
        final Address foundAddress = this.addressService.findById(id);
        return new ResponseEntity<>(foundAddress, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Long id) {
        this.addressService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
