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

    @OneToOne
    @JoinColumn(name = "promotion_id")
    private Promotion promotion;

    @OneToMany(mappedBy = "product")
    private List<Comment> comments;

    public Product(Promotion promotion, String name, float prize) {
        this.promotion = promotion;
        this.prize = prize;
        this.name = name;
    }

    public Product(Promotion promotion, Long id, String name, float prize) {
        this(promotion, name, prize);
        this.id = id;
    }
}
