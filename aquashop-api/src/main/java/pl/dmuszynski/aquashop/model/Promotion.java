package pl.dmuszynski.aquashop.model;

import org.springframework.data.annotation.CreatedDate;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import lombok.NoArgsConstructor;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Data @NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "promotion_id")
    private Long id;

    @OneToMany(mappedBy = "promotion")
    private List<Product> products;

    @NotBlank
    private String name;

    @Range(min = 0, max = 100)
    private int percentValue;

    @CreatedDate
    @NotNull @Column(updatable = false)
    private LocalDateTime creationDate;

    @CreatedDate
    @NotNull @Column(updatable = false)
    private LocalDateTime endDate;

    public Promotion(String name, int percentValue, LocalDateTime creationDate, LocalDateTime endTime) {
        this.name = name;
        this.percentValue = percentValue;
        this.creationDate = creationDate;
        this.endDate = endTime;
    }

    public Promotion(Long id, String name, int percentValue, LocalDateTime creationDate, LocalDateTime endTime) {
        this(name, percentValue, creationDate, endTime);
        this.id = id;
    }
}