package pl.dmuszynski.aquashop.payload;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO implements Serializable {
    private Long id;
    private String country;
    private String location;
    private String zipCode;
    private String street;
}
