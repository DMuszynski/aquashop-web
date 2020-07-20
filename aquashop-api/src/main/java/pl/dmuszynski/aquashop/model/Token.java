package pl.dmuszynski.aquashop.model;

import javax.persistence.*;

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

    public Token(User user, String value) {
        this.user = user;
        this.value = value;
    }

    public Token() { }

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
