package com.example.spring.service;

import com.example.spring.dto.request.UpdateStock;
import com.example.spring.entity.Share;
import com.example.spring.repository.ShareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UpdatePriceService {

    private final ShareRepository repository;
    private final StockPriceService stockPriceService;

    @Autowired
    public UpdatePriceService(ShareRepository repository, StockPriceService stockPriceService) {
        this.repository = repository;
        this.stockPriceService = stockPriceService;
    }

    public void updateStockPrice(UpdateStock updateStock) throws Exception {
        Share updatedShare = repository.findByShareName(updateStock.getShareName());
        if (updatedShare != null) {
            updatedShare.setCurrentPrice(Float.parseFloat(stockPriceService.getStockPrice(updateStock.getShareName())));
            updatedShare.setReturns(updatedShare.getBoughtPrice() - updatedShare.getCurrentPrice());
            repository.save(updatedShare);  // Save the updated share to the repository
        } else {
            // Handle the case where the share is not found
            throw new RuntimeException("Share not found: " + updateStock.getShareName());
        }
    }
}
