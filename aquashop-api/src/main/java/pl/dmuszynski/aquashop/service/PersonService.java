package pl.dmuszynski.aquashop.service;

import pl.dmuszynski.aquashop.payload.dto.PersonDTO;

public interface PersonService {
    PersonDTO addUserPerson(PersonDTO person, Long userId);
    PersonDTO updatePerson(PersonDTO person, Long id);
    PersonDTO findById(Long id);
    void deleteById(Long id);
}
