package pl.dmuszynski.aquashop.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "token_id", unique = true, nullable = false)
    private Long id;

    @Column(unique = true, nullable = false)
    private String value;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
