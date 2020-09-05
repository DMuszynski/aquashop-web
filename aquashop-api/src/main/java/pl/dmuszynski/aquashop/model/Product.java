package pl.dmuszynski.aquashop.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.persistence.*;
import java.util.List;

import lombok.experimental.Tolerate;
import lombok.Builder;
import lombok.Data;

@Entity
@Builder @Data
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

    @Tolerate
    public Product() {}
}
