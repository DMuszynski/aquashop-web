package pl.dmuszynski.aquashop.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id", unique = true)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String name;

    private String surname;

    @Column(unique = true, length = 9)
    private String phoneNumber;

    private LocalDate dateOfBirth;

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

    public void setUser(User user) {
        this.user = user;
    }
}

