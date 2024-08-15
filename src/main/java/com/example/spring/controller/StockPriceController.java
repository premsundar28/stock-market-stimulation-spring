package com.example.spring.controller;

import com.example.spring.dto.request.BuyStockRequest;
import com.example.spring.service.StockPurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.spring.service.StockPriceService;

@RestController
public class StockPriceController {

    @Autowired
    private StockPriceService stockPriceService;


    @Autowired
    private StockPurchaseService stockPurchaseService;

    @GetMapping("/stock/{symbol}")
    public String getStockPrice(@PathVariable String symbol) {
        try {
            return stockPriceService.getStockPrice(symbol);
        } catch (Exception e) {
            return "Error fetching stock price: " + e.getMessage();
        }
    }

    @PostMapping("/buy")
    public String buyStock(@RequestBody BuyStockRequest request) {
        try {
            return stockPurchaseService.buyStock(request);
        } catch (Exception e) {
            return "Error purchasing stock: " + e.getMessage();
        }
    }
}