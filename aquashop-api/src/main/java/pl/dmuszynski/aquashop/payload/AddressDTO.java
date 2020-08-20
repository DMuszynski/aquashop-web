package pl.dmuszynski.aquashop.payload;

import java.io.Serializable;

import lombok.Data;

@Data
public class AddressDTO implements Serializable {
    private Long id;
    private String country;
    private String location;
    private String zipCode;
    private String street;
}
