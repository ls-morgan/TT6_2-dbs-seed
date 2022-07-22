package com.dbs.repositories;

import com.dbs.entities.ExchangeRate;
import com.dbs.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExchangeRepository extends JpaRepository<ExchangeRate, Integer> {
}
