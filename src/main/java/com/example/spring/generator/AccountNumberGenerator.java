package com.example.spring.generator;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.UUID;

public class AccountNumberGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        // Generate a custom demat number, for example, using UUID or any other logic
        return "DEM" + UUID.randomUUID().toString().replace("-", "").substring(0, 15).toUpperCase();
    }
}