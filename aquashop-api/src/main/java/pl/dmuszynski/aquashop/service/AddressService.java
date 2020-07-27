package pl.dmuszynski.aquashop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.dmuszynski.aquashop.exception.AddressNotFoundException;
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

    public void save(Address address) {
        addressRepository.save(address);
    }

    public Iterable<Address> findAllById(Long id) {
        return addressRepository.findAllById(Collections.singleton(id));
    }

    public void deleteById(Long id) {
        addressRepository.deleteById(id);
    }
}
