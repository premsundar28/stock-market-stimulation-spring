package com.example.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {

    PENDING("Pending"),
    SUCCESS("Success"),
    ERROR("Error");

    private String label;
}
