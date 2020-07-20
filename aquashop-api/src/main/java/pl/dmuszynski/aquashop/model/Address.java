package pl.dmuszynski.aquashop.model;

import javax.persistence.*;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id", unique = true, nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String country;

    private String location;

    private String zipCode;

    private String street;

    public Address(User user, String country, String location, String zipCode, String street) {
        this.user = user;
        this.country = country;
        this.location = location;
        this.zipCode = zipCode;
        this.street = street;
    }

    public Address() { }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getCountry() {
        return country;
    }

    public String getLocation() {
        return location;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getStreet() {
        return street;
    }
}
