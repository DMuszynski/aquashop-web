package pl.dmuszynski.aquashop.model;

import javax.validation.constraints.NotBlank;
import javax.persistence.*;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @NotBlank
    private String country;

    @NotBlank
    private String location;

    @NotBlank
    private String zipCode;

    @NotBlank
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
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
