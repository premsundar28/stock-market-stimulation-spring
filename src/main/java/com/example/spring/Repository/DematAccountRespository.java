package com.example.spring.repository;

import com.example.spring.entity.DematAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DematAccountRespository extends JpaRepository<DematAccount,String> {

    DematAccount findByDematNumber(String dematNumber);

    DematAccount findByAccountHolderUsername(String username);
}
