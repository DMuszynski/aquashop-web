package pl.dmuszynski.aquashop.model;

import javax.validation.constraints.NotBlank;
import javax.persistence.*;

import lombok.NoArgsConstructor;
import lombok.Data;

@Entity
@Data @NoArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "value"))
public class Token {
    @Id
    private Long id;

    @NotBlank
    private String value;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    public Token(User user, String value) {
        this.user = user;
        this.value = value;
    }
}
