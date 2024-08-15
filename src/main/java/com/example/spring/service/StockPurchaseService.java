package com.example.spring.service;

import com.example.spring.dto.request.BuyStockRequest;
import com.example.spring.entity.DematAccount;
import com.example.spring.entity.Share;
import com.example.spring.repository.DematAccountRespository;
import com.example.spring.repository.ShareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockPurchaseService {

    @Autowired
    private DematAccountRespository dematAccountRespository;

    @Autowired
    private ShareRepository shareRepository;

    @Autowired
    private StockPriceService stockPriceService;  // Service to get the current stock price

    public String buyStock(BuyStockRequest request) throws Exception {
        // Retrieve the Demat Account
        DematAccount dematAccount = (DematAccount) dematAccountRespository.findByDematNumber(request.getDematNumber())
                .orElseThrow(() -> new RuntimeException("Demat account not found"));

        // Get the current stock price
        float currentPrice = Float.parseFloat(stockPriceService.getStockPrice(request.getSymbol()));

        // Create a new Share
        Share share = new Share();
        share.setShareName(request.getSymbol());
        share.setNumberOfShares(request.getNumberOfShares());
        share.setBoughtPrice(currentPrice);
        share.setCurrentPrice(currentPrice);
        share.setDematAccount(dematAccount);

        // Save the Share to the database
        shareRepository.save(share);

        // Add the share to the DematAccount's collection
        dematAccount.getShares().add(share);
        dematAccountRespository.save(dematAccount);

        return "Stock purchased successfully";
    }
}