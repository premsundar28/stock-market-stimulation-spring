package com.example.spring.dto.request;

import lombok.Data;

@Data
public class BuyStockRequest {
    private String symbol;
    private int numberOfShares;
}