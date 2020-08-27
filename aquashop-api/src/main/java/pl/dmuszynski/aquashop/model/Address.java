package pl.dmuszynski.aquashop.model;

import javax.validation.constraints.NotBlank;
import javax.persistence.*;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Data;

@Entity
@Data @NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Address extends AbstractEntity {

    @NotBlank
    private String country;

    @NotBlank
    private String location;

    @NotBlank
    private String zipCode;

    @NotBlank
    private String street;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Address(User user, String country, String location, String zipCode, String street) {
        this.user = user;
        this.country = country;
        this.location = location;
        this.zipCode = zipCode;
        this.street = street;
    }

    public Address(Long id, User user, String country, String location, String zipCode, String street) {
        this(user, country, location, zipCode, street);
    }
}
