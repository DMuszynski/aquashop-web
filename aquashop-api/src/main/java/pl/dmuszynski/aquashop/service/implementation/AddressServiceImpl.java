package pl.dmuszynski.aquashop.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.dmuszynski.aquashop.repository.AddressRepository;
import pl.dmuszynski.aquashop.service.AddressService;
import pl.dmuszynski.aquashop.service.UserService;
import pl.dmuszynski.aquashop.model.Address;
import pl.dmuszynski.aquashop.model.User;

import javax.transaction.Transactional;

@Service @Transactional
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final UserService userService;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository, UserService userService) {
        this.addressRepository = addressRepository;
        this.userService = userService;
    }

    @Override
    public void addUserAddress(Address address, Long userId) {
        final User user = this.userService.findById(userId);
        address.setUser(user);

        this.addressRepository.save(address);
    }

    @Override
    public void updateCountryById(String country, Long id) {
        this.addressRepository.updateCountryById(country, id);
    }

    @Override
    public void updateLocationById(String location, Long id) {
        this.addressRepository.updateLocationById(location, id);
    }

    @Override
    public void updateZipCodeById(String zipCode, Long id) {
        this.addressRepository.updateZipCodeById(zipCode, id);
    }

    @Override
    public void updateStreetById(String street, Long id) {
        this.addressRepository.updateStreetById(street, id);
    }

    @Override
    public void deleteById(Long id) {
        addressRepository.deleteById(id);
    }
}
