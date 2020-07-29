package pl.dmuszynski.aquashop.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.dmuszynski.aquashop.exception.AddressNotFoundException;
import pl.dmuszynski.aquashop.model.User;
import pl.dmuszynski.aquashop.repository.AddressRepository;
import pl.dmuszynski.aquashop.model.Address;
import pl.dmuszynski.aquashop.repository.UserRepository;

import java.util.Collections;
import java.util.Optional;

@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository, UserRepository userRepository) {
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
    }

    public Address findById(Long id) {
        return addressRepository.findById(id)
            .orElseThrow(() -> new AddressNotFoundException(id));
    }

    public void addUserAddress(Address address, Long id) {

//        this.addressRepository.findById(id)
//            .map(address1 -> {
//                a
//            })
//        User user = new User.UserBuilder("d","").build();
//        user.getAddresses().add(address);
//
//        addressRepository.save(new Address(user, address.getCountry(), address.getLocation(), address.getZipCode(), address.getStreet()));
    }

    public Iterable<Address> findAllById(Long id) {
        return addressRepository.findAllById(Collections.singleton(id));
    }

    public void deleteById(Long id) {
        addressRepository.deleteById(id);
    }
}
