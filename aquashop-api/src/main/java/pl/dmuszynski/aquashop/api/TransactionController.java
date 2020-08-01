package pl.dmuszynski.aquashop.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.dmuszynski.aquashop.model.Transaction;
import pl.dmuszynski.aquashop.service.TransactionService;

@RestController
@RequestMapping("/transaction-management/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public void realizeTransaction(@RequestBody Transaction transaction) {
        this.transactionService.realizeTransaction(transaction);
    }
}
