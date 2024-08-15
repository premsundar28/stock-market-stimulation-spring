package com.example.spring.controller;

import com.example.spring.repository.DematAccountRespository;
import com.example.spring.repository.UserRepository;
import com.example.spring.dto.request.DematAccountRequest;
import com.example.spring.entity.DematAccount;
import com.example.spring.entity.User;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/demat")
public class DematAccountController {

    @Autowired
    private DematAccountRespository dematAccountRespository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/create")
    public ResponseEntity<String> createDematAccount(@RequestBody DematAccountRequest dematAccountRequest) {
        // Find the user by ID
        User user = userRepository.findById(dematAccountRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Create a new DematAccount
        DematAccount dematAccount = new DematAccount();
        dematAccount.setAccountHolder(user);

        // Save the DematAccount to the database
        dematAccountRespository.save(dematAccount);

        return ResponseEntity.ok("Demat account created successfully with number: " + dematAccount.getDematNumber());
    }
}
