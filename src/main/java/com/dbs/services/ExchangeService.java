package com.dbs.services;

import com.dbs.entities.ExchangeRate;
import com.dbs.exceptions.ExchangeRateNotFoundException;
import com.dbs.repositories.ExchangeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class ExchangeService {

    @Autowired
    private ExchangeRepository exchangeRepository;

    public ExchangeRate getExchangeRate(String exchangeCurrency) {
        log.info("Getting exchangeRate id ");
        return exchangeRepository.findByExchangeCurrency(exchangeCurrency);
    }


    @Transactional(readOnly = true)
    public List<ExchangeRate> getAllExchangeRates() {
        log.info("Getting all exchange rates");
        return exchangeRepository.findAll();
    }

    @Transactional(readOnly = true)
    public ExchangeRate getExchangeRate(int id) {
        log.info(String.format("Getting exchangeRate id %s", id));
        return exchangeRepository.findById(id).orElseThrow(
                () -> new ExchangeRateNotFoundException(String.format("ExchangeRate id %s does not exist", id)));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ExchangeRate createExchangeRate(ExchangeRate exchangeRate) {
        return exchangeRepository.save(exchangeRate);
    }

    @Transactional
    public ExchangeRate updateExchangeRate(ExchangeRate exchangeRate, int id) {
        log.info(String.format("Updating exchangeRate id %s", id));
        ExchangeRate existingExchangeRate = getExchangeRate(id);
        existingExchangeRate.setRate(exchangeRate.getRate());
        return exchangeRepository.save(existingExchangeRate);
    }

    public void deleteExchangeRate(int id) {
        log.info(String.format("Deleting exchangeRate id %s", id));
        ExchangeRate existingExchangeRate = getExchangeRate(id);
        exchangeRepository.delete(existingExchangeRate);
    }

}
