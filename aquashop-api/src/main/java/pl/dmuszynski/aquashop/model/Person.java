package pl.dmuszynski.aquashop.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id", unique = true, nullable = false)
    private Long id;

    private String name;

    private String surname;

    private String phoneNumber;

    private LocalDate dateOfBirth;
}

