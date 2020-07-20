package pl.dmuszynski.aquashop.model;

import javax.persistence.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", unique = true, nullable = false)
    private Long id;

    private String name;

    public Product(String name) {
        this.name = name;
    }

    public Product() { }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
