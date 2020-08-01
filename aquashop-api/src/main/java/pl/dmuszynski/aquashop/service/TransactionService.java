package pl.dmuszynski.aquashop.service;

import pl.dmuszynski.aquashop.model.Transaction;

public interface TransactionService {
    void realizeTransaction(Transaction transaction);
}
