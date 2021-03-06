package pl.dmuszynski.aquashop.service.implementation;

import pl.dmuszynski.aquashop.repository.TransactionRepository;
import pl.dmuszynski.aquashop.payload.TransactionDTO;
import pl.dmuszynski.aquashop.service.TransactionService;
import pl.dmuszynski.aquashop.service.ProductService;
import pl.dmuszynski.aquashop.service.UserService;
import pl.dmuszynski.aquashop.model.Transaction;
import pl.dmuszynski.aquashop.model.Product;
import pl.dmuszynski.aquashop.model.User;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import lombok.RequiredArgsConstructor;

import java.util.stream.Collectors;
import java.util.List;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service(value = "transactionService")
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final ProductService productService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Override
    public TransactionDTO realizeTransaction(TransactionDTO transactionDetails) {
        final Product foundProduct = this.productService.findProductById(transactionDetails.getProduct().getId());
        final User foundUser = this.userService.findUserById(transactionDetails.getUser().getId());
        final Transaction savedTransaction = this.transactionRepository
            .save(new Transaction(
                foundUser,
                foundProduct,
                LocalDateTime.now())
            );

        return this.modelMapper.map(savedTransaction, TransactionDTO.class);
    }

    @Override
    public List<TransactionDTO> findAllTransactionDtoByUserId(Long userId) {
        final List<Transaction> foundTransactionList = this.transactionRepository.findAllByUserId(userId);
        return foundTransactionList.stream()
            .map(transaction -> this.modelMapper.map(transaction, TransactionDTO.class))
            .collect(Collectors.toList());
    }
}
