package pl.dmuszynski.aquashop.service;

import pl.dmuszynski.aquashop.model.Address;

public interface AddressService {
    void addUserAddress(Address address, Long userId);
    void updateCountryById(String country, Long id);
    void updateLocationById(String location, Long id);
    void updateZipCodeById(String zipCode, Long id);
    void updateStreetById(String street, Long id);
    void deleteById(Long id);
}
