package pl.dmuszynski.aquashop.service;

import pl.dmuszynski.aquashop.payload.PersonDTO;

public interface PersonService {
    PersonDTO addUserPerson(PersonDTO personDetails, Long userId);
    PersonDTO updatePerson(PersonDTO personDetails, Long id);
    PersonDTO findById(Long id);
    void deleteById(Long id);
}
