package pl.dmuszynski.aquashop.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.dmuszynski.aquashop.service.AddressService;
import pl.dmuszynski.aquashop.model.Address;

@RestController
@RequestMapping("user-management/users/{id}/address-management/addresses")
public class AddressController {

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping
    public void addUserAddress(@RequestBody Address address, @PathVariable("id") Long userId) {
        this.addressService.addUserAddress(address, userId);
    }

    @PatchMapping(value = "/{id}/country")
    public void updateCountryById(@RequestBody Address address, @PathVariable Long id) {
        this.addressService.updateCountryById(address.getCountry(), id);
    }

    @PatchMapping(value = "/{id}/location")
    public void updateLocationById(@RequestBody Address address, @PathVariable Long id) {
        this.addressService.updateLocationById(address.getLocation(), id);
    }

    @PatchMapping(value = "/{id}/zip-code")
    public void updateZipCodeById(@RequestBody Address address, @PathVariable Long id) {
        this.addressService.updateZipCodeById(address.getZipCode(), id);
    }

    @PatchMapping(value = "/{id}/street")
    public void updateStreetById(@RequestBody Address address, @PathVariable Long id) {
        this.addressService.updateStreetById(address.getStreet(), id);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteById(@PathVariable Long id) {
        this.addressService.deleteById(id);
    }
}
