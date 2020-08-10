package pl.dmuszynski.aquashop.service;

import pl.dmuszynski.aquashop.model.Address;

public interface AddressService {
    Address addUserAddress(Address address, Long userId);
    Address updateCountryById(String country, Long id);
    Address updateLocationById(String location, Long id);
    Address updateZipCodeById(String zipCode, Long id);
    Address updateStreetById(String street, Long id);
    void deleteById(Long id);
}
