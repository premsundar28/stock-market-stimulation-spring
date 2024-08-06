package com.example.spring.controller;

import com.example.spring.service.StockPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockPriceController {

    @Autowired
    private StockPriceService stockPriceService;

    @GetMapping("/stock/{symbol}")
    public String getStockPrice(@PathVariable String symbol) {
        try {
            return stockPriceService.getStockPrice(symbol);
        } catch (Exception e) {
            return "Error fetching stock price: " + e.getMessage();
        }
    }
}