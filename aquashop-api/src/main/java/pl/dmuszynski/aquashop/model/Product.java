package pl.dmuszynski.aquashop.model;

import javax.validation.constraints.NotNull;
import javax.persistence.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", unique = true, nullable = false)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    @Column(precision = 2)
    private float prize;

    public Product(String name, float prize) {
        this.name = name;
        this.prize = prize;
    }

    protected Product() { }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getPrize() {
        return prize;
    }
}
