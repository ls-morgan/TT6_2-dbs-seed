package com.dbs.services;

import com.dbs.controllers.requests.TransactionRequest;
import com.dbs.entities.Currency;
import com.dbs.entities.User;
import com.dbs.entities.Transaction;
import com.dbs.entities.Wallet;
import com.dbs.exceptions.TransactionNotFoundException;
import com.dbs.repositories.UserRepository;
import com.dbs.repositories.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private WalletService walletService;

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private ExchangeService exchangeService;

    @Transactional(readOnly = true)
    public List<Transaction> getTransactionByWalletId(int userId) {
        log.info(String.format("Getting transaction id %s", userId));
        return transactionRepository.findByWalletId(userId);
    }

    @Transactional(readOnly = true)
    public List<Transaction> getAllTransactions() {
        log.info("Getting all transactions");
        return transactionRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Transaction getTransaction(int id) {
        log.info(String.format("Getting transaction id %s", id));
        return transactionRepository.findById(id).orElseThrow(
                () -> new TransactionNotFoundException(String.format("Transaction id %s does not exist", id)));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Transaction createTransaction(TransactionRequest transactionRequest) {
        BigDecimal creditBalance;
        BigDecimal debitBalance;

        BigDecimal debitRate = exchangeService.getExchangeRate(transactionRequest.getDebitCurrency()).getRate();

        BigDecimal creditRate = exchangeService.getExchangeRate(transactionRequest.getCreditCurrency()).getRate();
        BigDecimal creditAmount = transactionRequest.getAmount().divide(debitRate,5,RoundingMode.HALF_DOWN).multiply(creditRate);

        List<Currency> currencies = currencyService.getAllCurrenciesForWallet(transactionRequest.getWalletId());
        for (Currency currency : currencies) {
            if (currency.getCurrency().equals(transactionRequest.getCreditCurrency())) {
                creditBalance = currency.getAmount().add(creditAmount);
                currency.setAmount(creditBalance);
                currencyService.updateCurrency(currency);
            }
            if (currency.getCurrency().equals(transactionRequest.getDebitCurrency())) {
                debitBalance = currency.getAmount().subtract(transactionRequest.getAmount());
                currency.setAmount(debitBalance);
                currencyService.updateCurrency(currency);
            }
        }

        Transaction transaction = Transaction.builder()
                .walletId(transactionRequest.getWalletId())
                .debitId(transactionRequest.getDebitId())
                .debitCurrency(transactionRequest.getDebitCurrency())
                .debitAmount(transactionRequest.getAmount())
                .creditId(transactionRequest.getCreditId())
                .creditCurrency(transactionRequest.getCreditCurrency())
                .creditAmount(creditAmount)
                .description(transactionRequest.getDescription())
                .build();
        return transactionRepository.save(transaction);
    }

//    @Transactional
//    public Transaction updateTransaction(Transaction transaction, int id) {
//        log.info(String.format("Updating transaction id %s", id));
//        Transaction existingTransaction = getTransaction(id);
//        existingTransaction.setName(transaction.getName());
//        return transactionRepository.save(existingTransaction);
//    }

    public void deleteTransaction(int id) {
        log.info(String.format("Deleting transaction id %s", id));
        Transaction existingTransaction = getTransaction(id);
        transactionRepository.delete(existingTransaction);
    }

}
