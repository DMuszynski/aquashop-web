package pl.dmuszynski.aquashop.payload.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class TransactionDTO {
    private Long id;
    private UserDTO user;
    private ProductDTO product;
    private LocalDateTime transactionDate;
}
