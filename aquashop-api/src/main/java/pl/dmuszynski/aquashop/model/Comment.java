package pl.dmuszynski.aquashop.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id", unique = true)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @NotNull
    private String description;

    public Comment(Product product, String description) {
        this.description = description;
        this.product = product;
    }

    protected Comment() {}

    public Long getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public String getDescription() {
        return description;
    }
}
