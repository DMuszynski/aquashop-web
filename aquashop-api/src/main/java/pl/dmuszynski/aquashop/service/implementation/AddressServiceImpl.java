package pl.dmuszynski.aquashop.service.implementation;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import pl.dmuszynski.aquashop.payload.AddressDTO;
import pl.dmuszynski.aquashop.repository.AddressRepository;
import pl.dmuszynski.aquashop.service.AddressService;
import pl.dmuszynski.aquashop.service.UserService;
import pl.dmuszynski.aquashop.model.Address;
import pl.dmuszynski.aquashop.model.User;

import java.util.stream.Collectors;
import java.util.List;

@Service(value = "addressService")
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository, UserService userService, ModelMapper modelMapper) {
        this.addressRepository = addressRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public AddressDTO addUserAddress(AddressDTO addressDetails, Long userId) {
        final User foundUser = this.userService.findUserById(userId);
        final Address savedAddress = this.addressRepository
            .save(new Address(
                foundUser,
                addressDetails.getCountry(),
                addressDetails.getLocation(),
                addressDetails.getZipCode(),
                addressDetails.getStreet())
            );

        return this.modelMapper.map(savedAddress, AddressDTO.class);
    }

    @Override
    public AddressDTO updateAddress(AddressDTO addressDetails, Long id) throws ResourceNotFoundException {
        final Address foundAddress = this.addressRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Address not found for this id" + id));

        final Address updatedAddress = this.addressRepository
            .save(new Address(
                foundAddress.getUser(),
                foundAddress.getId(),
                addressDetails.getCountry(),
                addressDetails.getLocation(),
                addressDetails.getZipCode(),
                addressDetails.getStreet())
            );

        return this.modelMapper.map(updatedAddress, AddressDTO.class);
    }

    @Override
    public List<AddressDTO> findAllAddressDtoByUserId(Long userId) {
        final List<Address> foundAddressList = this.userService.findUserById(userId).getAddresses();
        return foundAddressList.stream()
            .map(address -> this.modelMapper.map(address, AddressDTO.class))
            .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        this.addressRepository.deleteById(id);
    }
}
