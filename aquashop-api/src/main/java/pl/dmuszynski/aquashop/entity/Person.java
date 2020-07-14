package pl.dmuszynski.aquashop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter @Setter
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id", unique = true, nullable = false)
    private Long id;

//    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
//    @OneToOne
//    @MapsId
//    private User user;

//    @OneToOne(mappedBy = "person")
//    private User user;

    private String name;

    private String surname;

    private String phoneNumber;

    private LocalDate birth;
}

