package com.example.spring.repository;

import com.example.spring.entity.CurrentPrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrentPriceRepository extends JpaRepository<CurrentPrice, Long> {

    // The correct method name based on the column name in the entity
    CurrentPrice findByStockName(String stockName);
}
