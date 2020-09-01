package pl.dmuszynski.aquashop.payload;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO implements Serializable {
    private Long id;
    private String name;
    private String surname;
    private String phoneNumber;
    private LocalDate dateOfBirth;
}
