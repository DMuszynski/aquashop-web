package pl.dmuszynski.aquashop.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.persistence.*;
import java.time.LocalDate;

import lombok.NoArgsConstructor;
import lombok.Data;

@Entity
@Data @NoArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "phone_number"))
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    @NotBlank @Column(name = "phone_number", length = 9)
    private String phoneNumber;

    @NotNull
    private LocalDate dateOfBirth;

    public Person(User user, String name, String surname, String phoneNumber, LocalDate dateOfBirth) {
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.surname = surname;
        this.name = name;
        this.user = user;
    }

    public Person(User user, Long id, String name, String surname, String phoneNumber, LocalDate dateOfBirth) {
        this(user, name, surname, phoneNumber, dateOfBirth);
        this.id = id;
    }
}

