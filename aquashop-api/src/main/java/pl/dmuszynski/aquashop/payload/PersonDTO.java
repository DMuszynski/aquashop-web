package pl.dmuszynski.aquashop.payload;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

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
