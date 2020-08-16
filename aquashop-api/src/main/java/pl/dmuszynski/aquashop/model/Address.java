package pl.dmuszynski.aquashop.model;

import javax.validation.constraints.NotBlank;
import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor
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

    public Address(User user, Long id, String country, String location, String zipCode, String street) {
        this(user, country, location, zipCode, street);
        this.id = id;
    }
}
