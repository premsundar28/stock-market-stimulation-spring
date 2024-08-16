package com.example.spring.Security;

import com.example.spring.entity.DematAccount;
import com.example.spring.repository.DematAccountRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DematAccountService {

    private final DematAccountRespository dematAccountRespository;

    public String getDematAccountByUsername(String username) {
        DematAccount dematAccount = dematAccountRespository.findByAccountHolderUsername(username);
        return dematAccount.getDematNumber();
    }

}
