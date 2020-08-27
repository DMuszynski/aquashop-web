package pl.dmuszynski.aquashop.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import pl.dmuszynski.aquashop.service.AddressService;
import pl.dmuszynski.aquashop.payload.AddressDTO;

import lombok.RequiredArgsConstructor;

import javax.validation.Valid;
import java.util.List;

@RestController
@PreAuthorize(value = "hasRole('USER')")
@RequestMapping(value = "/user-management/users/{userId}/address-management/addresses")
public class AddressController {

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping
    public ResponseEntity<AddressDTO> addUserAddress(@RequestBody @Valid AddressDTO addressDetails, @PathVariable Long userId) {
        final AddressDTO createdAddressDto = this.addressService.addUserAddress(addressDetails, userId);
        return new ResponseEntity<>(createdAddressDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<AddressDTO> updateAddress(@RequestBody @Valid AddressDTO addressDetails, @PathVariable Long id) {
        final AddressDTO updatedAddressDto = this.addressService.updateAddress(addressDetails, id);
        return new ResponseEntity<>(updatedAddressDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<AddressDTO>> findAllAddressDtoByUserId(@PathVariable Long userId) {
        final List<AddressDTO> foundAddressDtoList = this.addressService.findAllAddressDtoByUserId(userId);
        if (!foundAddressDtoList.isEmpty())
            return new ResponseEntity<>(foundAddressDtoList, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Long id) {
        this.addressService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}