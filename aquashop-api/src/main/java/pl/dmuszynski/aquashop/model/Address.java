package pl.dmuszynski.aquashop.model;

import javax.validation.constraints.NotBlank;
import javax.persistence.*;

import lombok.experimental.Tolerate;
import lombok.Builder;
import lombok.Data;

@Entity
@Builder @Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String country;

    @NotBlank
    private String location;

    @NotBlank
    private String zipCode;

    @NotBlank
    private String street;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Tolerate
    public Address() {}
}
