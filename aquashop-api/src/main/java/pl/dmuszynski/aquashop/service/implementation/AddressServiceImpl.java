package pl.dmuszynski.aquashop.service.implementation;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import pl.dmuszynski.aquashop.payload.AddressDTO;
import pl.dmuszynski.aquashop.repository.AddressRepository;
import pl.dmuszynski.aquashop.service.AddressService;
import pl.dmuszynski.aquashop.service.UserService;
import pl.dmuszynski.aquashop.model.Address;
import pl.dmuszynski.aquashop.model.User;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Service(value = "addressService")
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Override
    public AddressDTO addUserAddress(AddressDTO addressDetails, Long userId) {
        final User foundUser = this.userService.findUserById(userId);
        final Address savedAddress = this.addressRepository
            .save(Address.builder()
                .user(foundUser)
                .country(addressDetails.getCountry())
                .location(addressDetails.getLocation())
                .zipCode(addressDetails.getZipCode())
                .street(addressDetails.getStreet())
                .build()
            );

        return this.modelMapper.map(savedAddress, AddressDTO.class);
    }

    @Override
    public AddressDTO updateAddress(AddressDTO addressDetails, Long addressId) throws ResourceNotFoundException {
        final Address foundAddress = this.findAddressById(addressId);
        final Address updatedAddress = this.addressRepository
            .save(Address.builder()
                .id(foundAddress.getId())
                .user(foundAddress.getUser())
                .country(addressDetails.getCountry())
                .location(addressDetails.getLocation())
                .zipCode(addressDetails.getZipCode())
                .street(addressDetails.getStreet())
                .build()
            );

        return this.modelMapper.map(updatedAddress, AddressDTO.class);
    }

    @Override
    public List<AddressDTO> findAllAddressDtoByUserId(Long userId) {
        return this.addressRepository.findAllAddressDtoByUserId(userId);
    }

    private Address findAddressById(Long id) throws ResourceNotFoundException {
        return this.addressRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Address not found for this id" + id));
    }

    @Override
    public void deleteById(Long id) {
        this.addressRepository.deleteById(id);
    }
}
