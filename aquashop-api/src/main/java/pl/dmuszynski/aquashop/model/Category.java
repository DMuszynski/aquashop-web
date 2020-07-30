package pl.dmuszynski.aquashop.model;

import javax.validation.constraints.NotNull;
import javax.persistence.*;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", unique = true, nullable = false)
    private Long id;

    @NotNull
    private String name;

    public Category(String name) {
        this.name = name;
    }

    protected Category() { }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
