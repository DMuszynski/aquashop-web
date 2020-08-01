package pl.dmuszynski.aquashop.model;

import javax.validation.constraints.NotNull;
import javax.persistence.*;

@Entity
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "token_id", unique = true)
    private Long id;

    @NotNull @Column(unique = true)
    private String value;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Token(User user, String value) {
        this.user = user;
        this.value = value;
    }

    protected Token() { }

    public Long getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public User getUser() {
        return user;
    }
}
