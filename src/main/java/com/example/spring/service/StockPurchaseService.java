package com.example.spring.service;

import com.example.spring.controller.AuthController;
import com.example.spring.dto.request.BuyStockRequest;
import com.example.spring.entity.DematAccount;
import com.example.spring.entity.Share;
import com.example.spring.repository.DematAccountRespository;
import com.example.spring.repository.ShareRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
public class StockPurchaseService {

    private static final Logger logger = LoggerFactory.getLogger(StockPurchaseService.class);


    @Autowired
    private DematAccountRespository dematAccountRespository;

    @Autowired
    private ShareRepository shareRepository;

    @Autowired
    private AuthController authController;

    @Autowired
    private StockPriceService stockPriceService;

    public String buyStock(BuyStockRequest request) throws Exception {
        // Retrieve the Demat Number for the current user
        String dematNumber = authController.getDematNumberForCurrentUser();

        // Fetch the DematAccount entity from the database
        Optional<DematAccount> dematAccountOptional = dematAccountRespository.findById(dematNumber);
        if (dematAccountOptional.isEmpty()) {
            throw new Exception("DematAccount not found for demat number: " + dematNumber);
        }
        DematAccount dematAccount = dematAccountOptional.get();

        // Get the current stock price
        float currentPrice = Float.parseFloat(stockPriceService.getStockPrice(request.getSymbol()));

        // Create a new Share entity
        Share share = new Share();
        share.setShareName(request.getSymbol());
        share.setNumberOfShares(request.getNumberOfShares());
        share.setBoughtPrice(currentPrice);
        share.setDematAccount(dematAccount);
        share.setReturns(0.0f);
        share.setLocalDateTime(LocalDateTime.now());


        dematAccountRespository.save(dematAccount);

        // Save the Share entity to the database
        shareRepository.save(share);



        return "Stock purchased successfully";
    }
}
