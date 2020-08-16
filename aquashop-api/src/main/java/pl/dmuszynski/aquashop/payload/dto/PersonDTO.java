package pl.dmuszynski.aquashop.payload.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class PersonDTO implements Serializable {
    private Long id;
    private String name;
    private String surname;
    private String phoneNumber;
    private LocalDate dateOfBirth;
}
