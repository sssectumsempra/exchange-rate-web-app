package com.exchangerate.app.repository;

import com.exchangerate.app.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ExchangeRateRepository extends JpaRepository<Currency, Long> {
    @Query("select c from Currency c where c.currencyCode = :currencyCode")
    Optional<Currency> findByCurrencyCode(@Param("currencyCode") String currencyCode);
}
