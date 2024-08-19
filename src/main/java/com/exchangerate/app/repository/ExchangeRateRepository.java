package com.exchangerate.app.repository;

import com.exchangerate.app.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeRateRepository extends JpaRepository<Currency, Long> {

}
