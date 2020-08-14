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
@RequestMapping(value = "user-management/users/{id}/address-management/addresses")
public class AddressController {

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping
    public ResponseEntity<Address> addUserAddress(@RequestBody Address address, @PathVariable(value = "id") Long userId) {
        final Address createdAddress = this.addressService.addUserAddress(address, userId);
        return new ResponseEntity<>(createdAddress, HttpStatus.CREATED);
    }

    @PatchMapping(value = "/{id}/country")
    public ResponseEntity<Address> updateCountryById(@RequestBody String country, @PathVariable Long id) {
        final Address updatedAddress = this.addressService.updateCountryById(country, id);
        return new ResponseEntity<>(updatedAddress, HttpStatus.OK);
    }

    @PatchMapping(value = "/{id}/location")
    public ResponseEntity<Address> updateLocationById(@RequestBody String location, @PathVariable Long id) {
        final Address updatedAddress = this.addressService.updateLocationById(location, id);
        return new ResponseEntity<>(updatedAddress, HttpStatus.OK);
    }

    @PatchMapping(value = "/{id}/zip-code")
    public ResponseEntity<Address> updateZipCodeById(@RequestBody String zipCode, @PathVariable Long id) {
        final Address updatedAddress = this.addressService.updateZipCodeById(zipCode, id);
        return new ResponseEntity<>(updatedAddress, HttpStatus.OK);
    }

    @PatchMapping(value = "/{id}/street")
    public ResponseEntity<Address> updateStreetById(@RequestBody String street, @PathVariable Long id) {
        final Address updatedAddress = this.addressService.updateStreetById(street, id);
        return new ResponseEntity<>(updatedAddress, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Long id) {
        this.addressService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
