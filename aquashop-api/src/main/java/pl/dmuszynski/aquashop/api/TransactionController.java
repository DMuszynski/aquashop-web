package pl.dmuszynski.aquashop.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import pl.dmuszynski.aquashop.service.TransactionService;
import pl.dmuszynski.aquashop.payload.TransactionDTO;
import lombok.RequiredArgsConstructor;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@PreAuthorize(value = "hasRole('USER')")
@RequestMapping(value = "/user-management/users/{userId}/transaction-management/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<TransactionDTO> realizeTransaction(@RequestBody @Valid TransactionDTO transactionDetails) {
        final TransactionDTO completedTransactionDto = this.transactionService.realizeTransaction(transactionDetails);
        return new ResponseEntity<>(completedTransactionDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TransactionDTO>> findAllTransactionDtoByUserId(@PathVariable Long userId) {
        final List<TransactionDTO> foundTransactionDtoList = this.transactionService.findAllTransactionDtoByUserId(userId);
        if (!foundTransactionDtoList.isEmpty())
            return new ResponseEntity<>(foundTransactionDtoList, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
