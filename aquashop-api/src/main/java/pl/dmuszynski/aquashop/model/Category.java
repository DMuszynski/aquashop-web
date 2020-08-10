package pl.dmuszynski.aquashop.model;

import javax.validation.constraints.NotBlank;
import javax.persistence.*;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @NotBlank
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
