package pl.dmuszynski.aquashop.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.annotation.CreatedDate;

import javax.validation.constraints.NotNull;
import javax.persistence.*;
import java.time.LocalDateTime;

import lombok.NoArgsConstructor;
import lombok.Data;

@Entity
@Data @NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @CreatedDate
    @NotNull @Column(updatable = false)
    private LocalDateTime transactionDate;

    public Transaction(User user, Product product, LocalDateTime transactionDate) {
        this.user = user;
        this.product = product;
        this.transactionDate = transactionDate;
    }
}

