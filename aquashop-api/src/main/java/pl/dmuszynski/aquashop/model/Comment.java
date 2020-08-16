package pl.dmuszynski.aquashop.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.persistence.*;

import lombok.NoArgsConstructor;
import lombok.Data;

@Entity
@Data @NoArgsConstructor
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
    private int rating;

    public Comment(Product product, String description, int rating) {
        this.description = description;
        this.product = product;
        this.rating = rating;
    }

    public Comment(Product product, Long id, String description, int rating) {
        this(product, description, rating);
        this.id = id;
    }
}
