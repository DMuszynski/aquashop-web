package pl.dmuszynski.aquashop.service;

import pl.dmuszynski.aquashop.payload.AddressDTO;

import java.util.List;

public interface AddressService {
    AddressDTO addUserAddress(AddressDTO addressDetails, Long userId);
    AddressDTO updateAddress(AddressDTO addressDetails, Long id);
    List<AddressDTO> findAllByUserId(Long userId);
    void deleteById(Long id);
}
