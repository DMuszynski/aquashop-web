package pl.dmuszynski.aquashop.model;

import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "phone_number"))
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private Long id;

    @OneToOne
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
        this.setId(id);
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}

