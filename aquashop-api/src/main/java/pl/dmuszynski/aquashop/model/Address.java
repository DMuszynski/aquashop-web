package pl.dmuszynski.aquashop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Address {

    private Long id;
    private String street;
    private String zipCode;
    private String location;
    private String country;
}
