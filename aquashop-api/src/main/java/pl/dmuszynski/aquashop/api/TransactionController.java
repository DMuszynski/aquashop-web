package pl.dmuszynski.aquashop.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import pl.dmuszynski.aquashop.service.TransactionService;
import pl.dmuszynski.aquashop.model.Transaction;

@RestController
@PreAuthorize(value = "hasRole('USER')")
@RequestMapping(value = "/transaction-management/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<Transaction> realizeTransaction(@RequestBody Transaction transaction) {
        final Transaction completedTransaction = this.transactionService.realizeTransaction(transaction);
        return new ResponseEntity<>(completedTransaction, HttpStatus.OK);
    }
}
