package pl.dmuszynski.aquashop.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id", unique = true)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String surname;

    @NotNull
    @Column(unique = true, length = 9)
    private String phoneNumber;

    @NotNull
    private LocalDate dateOfBirth;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Person(User user, String name, String surname, String phoneNumber, LocalDate dateOfBirth) {
        this.user = user;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
    }

    protected Person() { }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
}

