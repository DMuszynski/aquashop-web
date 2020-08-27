package pl.dmuszynski.aquashop.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.persistence.*;

import lombok.NoArgsConstructor;
import lombok.Data;

@Entity
@Data @NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @NotNull
    private Short grade;

    @NotBlank
    private String reviewComment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    public Review(Product product, Short grade, String reviewComment) {
        this.reviewComment = reviewComment;
        this.product = product;
        this.grade = grade;
    }

    public Review(Long id, Product product, Short grade, String reviewComment) {
        this(product, grade, reviewComment);
        this.id = id;
    }
}
