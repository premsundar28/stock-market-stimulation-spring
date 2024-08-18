package com.example.spring.service;

import com.example.spring.repository.CurrentPriceRepository ;
import com.example.spring.dto.request.CurrentPriceRequest;
import com.example.spring.entity.CurrentPrice;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.spring.repository.ShareRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class UpdatePriceService {

    private final CurrentPriceRepository currentPriceRepository;
    private final StockPriceService stockPriceService;
    private final ShareRepository shareRepository;

    public void updateStockPrice(CurrentPriceRequest currentPriceRequest) throws Exception {
        String stock_name = currentPriceRequest.getStock_name();
        Float currentPrice = Float.parseFloat(stockPriceService.getStockPrice(stock_name));

        // Retrieve the existing CurrentPrice entity by stock symbol
        CurrentPrice existingPrice = currentPriceRepository.findByStockName(stock_name);

        if (existingPrice == null) {
            // If no existing entry is found, create a new CurrentPrice entity
            existingPrice = new CurrentPrice();
            existingPrice.setStockName(stock_name);
        }

        // Update the price of the existing or new entity
        existingPrice.setCurrentPrice(currentPrice);

        // Save the updated entity
        currentPriceRepository.save(existingPrice);
    }
}
