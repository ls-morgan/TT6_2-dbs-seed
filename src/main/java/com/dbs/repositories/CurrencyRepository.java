package com.dbs.repositories;

import com.dbs.entities.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Integer> {
    Optional<Currency> findByWalletId(String walletId);
    List<Currency> findAllByWalletId(int walletId);
}
