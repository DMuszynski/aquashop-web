package pl.dmuszynski.aquashop.service;

import pl.dmuszynski.aquashop.payload.dto.AddressDTO;

import java.util.List;

public interface AddressService {
    AddressDTO addUserAddress(AddressDTO address, Long userId);
    AddressDTO updateAddress(AddressDTO address, Long id);
    List<AddressDTO> findAllByUserId(Long userId);
    void deleteById(Long id);
}
