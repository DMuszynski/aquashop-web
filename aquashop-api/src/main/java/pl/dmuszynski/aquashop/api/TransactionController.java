package pl.dmuszynski.aquashop.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import pl.dmuszynski.aquashop.service.TransactionService;
import pl.dmuszynski.aquashop.payload.TransactionDTO;

import javax.validation.Valid;
import java.util.List;

@RestController
@PreAuthorize(value = "hasRole('USER')")
@RequestMapping(value = "/user-management/users/{userId}/transaction-management/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<TransactionDTO> realizeTransaction(@RequestBody @Valid TransactionDTO transactionDetails) {
        final TransactionDTO completedTransactionDto = this.transactionService.realizeTransaction(transactionDetails);
        return new ResponseEntity<>(completedTransactionDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TransactionDTO>> findAllTransactionDtoByUserId(@PathVariable Long userId) {
        final List<TransactionDTO> foundTransactionList = this.transactionService.findAllByUserId(userId);

        if (!foundTransactionList.isEmpty())
            return new ResponseEntity<>(foundTransactionList, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
