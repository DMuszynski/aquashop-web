package pl.dmuszynski.aquashop.model;

import org.springframework.data.annotation.CreatedDate;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import lombok.NoArgsConstructor;
import lombok.Data;

@Entity
@Data @NoArgsConstructor
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "promotion_id")
    private Long id;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Product> products;

    @Range(min = 0, max = 100)
    private int percentValue;

    @CreatedDate
    @NotNull @Column(updatable = false)
    private LocalDateTime creationDate;

    @CreatedDate
    @NotNull @Column(updatable = false)
    private LocalDateTime endDate;

    public Promotion(int percentValue, List<Product> products, LocalDateTime creationDate, LocalDateTime endTime) {
        this.percentValue = percentValue;
        this.products = products;
        this.creationDate = creationDate;
        this.endDate = endTime;
    }

    public Promotion(Long id, int percentValue, List<Product> products, LocalDateTime creationDate, LocalDateTime endTime) {
        this(percentValue, products, creationDate, endTime);
        this.id = id;
    }
}