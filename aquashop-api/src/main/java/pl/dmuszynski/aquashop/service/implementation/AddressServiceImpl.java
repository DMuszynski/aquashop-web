package pl.dmuszynski.aquashop.service.implementation;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.dmuszynski.aquashop.repository.AddressRepository;
import pl.dmuszynski.aquashop.service.AddressService;
import pl.dmuszynski.aquashop.service.UserService;
import pl.dmuszynski.aquashop.model.Address;
import pl.dmuszynski.aquashop.model.User;

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
    public Address addUserAddress(Address address, Long userId) throws ResourceNotFoundException {
        final User user = this.userService.findById(userId);
        return this.addressRepository
            .save(new Address(user, address.getCountry(),
                address.getLocation(), address.getZipCode(), address.getStreet()));
    }

    @Override
    public Address updateAddress(Address addressDetails, Long id) throws ResourceNotFoundException {
        final Address address = this.findById(id);
        return this.addressRepository
            .save(new Address(address.getUser(),address.getId(), addressDetails.getCountry(),
                addressDetails.getLocation(), addressDetails.getZipCode(), addressDetails.getStreet()));
    }

    @Override
    public Address findById(Long id) throws ResourceNotFoundException {
        return this.addressRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Address not found for this id" + id));
    }

    @Override
    public void deleteById(Long id) {
        this.addressRepository.deleteById(id);
    }
}
