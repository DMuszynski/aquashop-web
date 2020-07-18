package pl.dmuszynski.aquashop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "token_id", unique = true, nullable = false)
    private Long id;

    @Column(length = 50)
    private String value;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
