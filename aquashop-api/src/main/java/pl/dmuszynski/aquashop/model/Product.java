package pl.dmuszynski.aquashop.model;

import javax.persistence.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", unique = true, nullable = false)
    private Long id;

    private String name;

    public Product(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Product() {
        this(0L, "Product");
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
