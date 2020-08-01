package pl.dmuszynski.aquashop.model;

import javax.validation.constraints.NotNull;
import javax.persistence.*;
import java.util.List;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", unique = true)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    @Column(precision = 2)
    private float prize;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    private List<Comment> comments;

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

    public List<Comment> getComments() {
        return comments;
    }
}
