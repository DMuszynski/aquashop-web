package pl.dmuszynski.aquashop.payload;

import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.Data;

@Data
public class TransactionDTO implements Serializable {
    private Long id;
    private UserDTO user;
    private ProductDTO product;
    private LocalDateTime transactionDate;
}
