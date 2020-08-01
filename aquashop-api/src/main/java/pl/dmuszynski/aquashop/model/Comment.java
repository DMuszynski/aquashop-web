package pl.dmuszynski.aquashop.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id", unique = true)
    private Long id;

    @NotNull
    private int mark;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @NotNull
    private String description;

    public Comment(Product product, String description, int mark) {
        this.description = description;
        this.product = product;
        this.mark = mark;
    }

    protected Comment() {}

    public Long getId() {
        return id;
    }

    public int getMark() {
        return mark;
    }

    public Product getProduct() {
        return product;
    }

    public String getDescription() {
        return description;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
