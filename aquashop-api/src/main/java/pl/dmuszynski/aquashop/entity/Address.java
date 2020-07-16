package pl.dmuszynski.aquashop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id", unique = true, nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(length = 35)
    private String street;

    @Column(length = 5)
    private String zipCode;

    @Column(length = 30)
    private String location;

    @Column(length = 25)
    private String country;
}
