package pl.dmuszynski.aquashop.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dmuszynski.aquashop.repository.TransactionRepository;
import pl.dmuszynski.aquashop.service.TransactionService;
import pl.dmuszynski.aquashop.model.Transaction;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionServiceImpl(final TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void realizeTransaction(Transaction transaction) {
        this.transactionRepository.save(transaction);
    }
}
