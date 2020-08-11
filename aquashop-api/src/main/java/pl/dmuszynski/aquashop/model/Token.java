package pl.dmuszynski.aquashop.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.persistence.*;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "value"))
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "token_id")
    private Long id;

    @NotBlank
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
