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
    private Float price;

    @OneToMany(mappedBy = "product")
    private List<Review> reviews;

    @OneToOne(mappedBy = "product", fetch = FetchType.LAZY)
    private Promotion promotion;

    public Product(String name, Float price) {
        this.price = price;
        this.name = name;
    }

    public Product(Long id, String name, Float price, List<Review> reviews) {
        this(name, price);
        this.reviews = reviews;
        this.id = id;
    }
}
