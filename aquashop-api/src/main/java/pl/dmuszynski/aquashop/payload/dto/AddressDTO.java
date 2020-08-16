package pl.dmuszynski.aquashop.payload.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class AddressDTO implements Serializable {
    private Long id;
    private String country;
    private String location;
    private String zipCode;
    private String street;
}
