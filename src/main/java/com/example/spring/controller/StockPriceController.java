package com.example.spring.controller;

import com.example.spring.dto.request.BuyStockRequest;
import com.example.spring.dto.request.UpdateStock;
import com.example.spring.service.StockPurchaseService;
import com.example.spring.service.UpdatePriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.spring.service.StockPriceService;

@RestController
@RequestMapping("/stock")
@RequiredArgsConstructor
public class StockPriceController {


    final private StockPriceService stockPriceService;
    final private UpdatePriceService updatePriceService;


    @Autowired
    private StockPurchaseService stockPurchaseService;

    @GetMapping("{symbol}")
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

    @PostMapping("/updatePrice")
    public void updatePrice(@RequestBody UpdateStock updateStock) throws Exception {
        updatePriceService.updateStockPrice(updateStock);
    }
}