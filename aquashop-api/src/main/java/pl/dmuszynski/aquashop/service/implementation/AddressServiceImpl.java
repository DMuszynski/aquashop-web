package pl.dmuszynski.aquashop.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.dmuszynski.aquashop.exception.AddressNotFoundException;
import pl.dmuszynski.aquashop.repository.AddressRepository;
import pl.dmuszynski.aquashop.service.AddressService;
import pl.dmuszynski.aquashop.service.UserService;
import pl.dmuszynski.aquashop.model.Address;
import pl.dmuszynski.aquashop.model.User;

import javax.transaction.Transactional;

@Transactional
@Service(value = "addressService")
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final UserService userService;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository, UserService userService) {
        this.addressRepository = addressRepository;
        this.userService = userService;
    }

    @Override
    public Address addUserAddress(Address address, Long userId) {
        final User user = this.userService.findById(userId);

        return this.addressRepository.save(
            new Address(user,
                address.getCountry(),
                address.getLocation(),
                address.getZipCode(),
                address.getStreet())
        );
    }

    @Override
    public Address updateCountryById(String country, Long id) {
        final Address address = this.addressRepository.findById(id)
            .orElseThrow(AddressNotFoundException::new);
        address.setCountry(country);

        this.addressRepository.updateCountryById(address.getCountry(), id);
        return address;
    }

    @Override
    public Address updateLocationById(String location, Long id) {
        final Address address = this.addressRepository.findById(id)
            .orElseThrow(AddressNotFoundException::new);
        address.setLocation(location);

        this.addressRepository.updateLocationById(address.getLocation(), id);
        return address;
    }

    @Override
    public Address updateZipCodeById(String zipCode, Long id) {
        final Address address = this.addressRepository.findById(id)
            .orElseThrow(AddressNotFoundException::new);
        address.setZipCode(zipCode);

        this.addressRepository.updateZipCodeById(address.getZipCode(), id);
        return address;
    }

    @Override
    public Address updateStreetById(String street, Long id) {
        final Address address = this.addressRepository.findById(id)
            .orElseThrow(AddressNotFoundException::new);
        address.setStreet(street);

        this.addressRepository.updateStreetById(address.getStreet(), id);
        return address;
    }

    @Override
    public void deleteById(Long id) {
        addressRepository.deleteById(id);
    }
}
