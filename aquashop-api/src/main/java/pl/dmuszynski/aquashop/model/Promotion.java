package pl.dmuszynski.aquashop.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.annotation.CreatedDate;
import org.hibernate.validator.constraints.Range;
import lombok.NoArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data @NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "promotion_id")
    private Long id;

    @Range(min = 0, max = 100)
    private int percentValue;

    @CreatedDate
    @NotNull @Column(updatable = false)
    private LocalDate startDate;

    @CreatedDate
    @NotNull @Column(updatable = false)
    private LocalDate endDate;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    public Promotion(Product product, int percentValue, LocalDate startDate, LocalDate endDate) {
        this.percentValue = percentValue;
        this.startDate = startDate;
        this.endDate = endDate;
        this.product = product;
    }

    public Promotion(Long id, Product product, int percentValue, LocalDate startDate, LocalDate endDate) {
        this(product, percentValue, startDate, endDate);
        this.id = id;
    }
}