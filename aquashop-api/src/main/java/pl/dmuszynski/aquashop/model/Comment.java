package pl.dmuszynski.aquashop.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.persistence.*;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "description"))
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @NotBlank
    private String description;

    @NotNull
    private int mark;

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

    public void setId(Long id) {
        this.id = id;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
