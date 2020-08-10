package pl.dmuszynski.aquashop.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id", unique = true)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    private String country;

    @NotNull
    private String location;

    @NotNull
    private String zipCode;

    @NotNull
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

    public void setUser(User user) {
        this.user = user;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
