package pl.dmuszynski.aquashop.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.persistence.*;
import java.util.List;

import lombok.NoArgsConstructor;
import lombok.Data;

@Entity
@Data @NoArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @NotBlank
    private String name;

    @NotNull @Column(scale = 2)
    private float prize;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    private List<Comment> comments;

    public Product(String name, float prize) {
        this.name = name;
        this.prize = prize;
    }

    public Product(Long id, String name, float prize) {
        this(name, prize);
        this.id = id;
    }
}
