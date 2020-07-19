package pl.dmuszynski.aquashop.model;

import javax.persistence.*;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", unique = true, nullable = false)
    private Long id;

    private String name;

    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category() {
        this(0L, "Category");
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
