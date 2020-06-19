package pl.dmuszynski.aquashop.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


public class User {

    private Long id;
    private String email;
    private String password;
    private String role;
    private String name;
    private String surname;
    private String phoneNumber;
    private LocalDate birth;
    private LocalDate created;
    private List<Address> addresses;
    private boolean isEnabled;
}
