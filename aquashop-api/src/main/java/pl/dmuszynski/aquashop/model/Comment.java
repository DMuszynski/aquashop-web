package pl.dmuszynski.aquashop.model;

import javax.persistence.*;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id", unique = true)
    private Long id;

    private String description;

    public Comment(String description) {
        this.description = description;
    }

    protected Comment() {}

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
