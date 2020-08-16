package pl.dmuszynski.aquashop.service;

import pl.dmuszynski.aquashop.payload.dto.TransactionDTO;

import java.util.List;

public interface TransactionService {
    TransactionDTO realizeTransaction(TransactionDTO transactionDetails);
    List<TransactionDTO> findAllByUserId(Long userId);
}
