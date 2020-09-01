package pl.dmuszynski.aquashop.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.persistence.*;
import java.time.LocalDate;

import lombok.Data;
import lombok.Builder;
import lombok.experimental.Tolerate;

@Entity
@Builder @Data
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "phone_number"))
public class Person {
    @Id
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    @NotBlank
    @Column(name="phone_number", length = 9)
    private String phoneNumber;

    @NotNull
    private LocalDate dateOfBirth;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @Tolerate
    public Person() {}
}

