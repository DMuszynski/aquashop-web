package pl.dmuszynski.aquashop.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dmuszynski.aquashop.model.Product;
import pl.dmuszynski.aquashop.model.User;
import pl.dmuszynski.aquashop.repository.TransactionRepository;
import pl.dmuszynski.aquashop.service.ProductService;
import pl.dmuszynski.aquashop.service.TransactionService;
import pl.dmuszynski.aquashop.model.Transaction;
import pl.dmuszynski.aquashop.service.UserService;

import java.time.LocalDateTime;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final ProductService productService;
    private final UserService userService;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository, ProductService productService,
                                  UserService userService)
    {
        this.transactionRepository = transactionRepository;
        this.productService = productService;
        this.userService = userService;
    }

    @Override
    public void realizeTransaction(Transaction transaction) {
        Product product = this.productService.findById(transaction.getId());
        User user = this.userService.findById(transaction.getId());

        this.transactionRepository.save(new Transaction(product, user, LocalDateTime.now()));
    }
}
