package pl.dmuszynski.aquashop.service;

import pl.dmuszynski.aquashop.model.Address;

public interface AddressService {
    Address addUserAddress(Address address, Long userId);
    Address updateAddress(Address address, Long id);
    Address findById(Long id);
    void deleteById(Long id);
}
