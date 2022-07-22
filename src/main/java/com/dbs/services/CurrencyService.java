package com.dbs.services;

import com.dbs.entities.Currency;
import com.dbs.exceptions.CurrencyNotFoundException;
import com.dbs.repositories.CurrencyRepository;
import com.dbs.repositories.CurrencyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class CurrencyService {

    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private WalletService walletService;

    public List<Currency> getAllCurrenciesForUser(int id) {
        log.info("Getting all currencies for user");
        return currencyRepository.findAllByWalletId(id);
    }

    @Transactional(readOnly = true)
    public List<Currency> getAllCurrencies() {
        log.info("Getting all currencies");
        return currencyRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Currency getCurrency(int id) {
        log.info(String.format("Getting currency id %s", id));
        return currencyRepository.findById(id).orElseThrow(
                () -> new CurrencyNotFoundException(String.format("Currency id %s does not exist", id)));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Currency createCurrency(Currency currency) {
        validateWallet(currency.getWalletId());
        return currencyRepository.save(currency);
    }

    @Transactional
    public Currency updateCurrency(Currency currency, int id) {
        log.info(String.format("Updating currency id %s", id));
        Currency existingCurrency = getCurrency(id);
        existingCurrency.setAmount(currency.getAmount());
        return currencyRepository.save(existingCurrency);
    }

    public void deleteCurrency(int id) {
        log.info(String.format("Deleting currency id %s", id));
        Currency existingCurrency = getCurrency(id);
        currencyRepository.delete(existingCurrency);
    }

    private void validateWallet(int id) {
        walletService.getWallet(id);
    }
}
