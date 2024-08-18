package com.example.spring.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
@Table(name = "current_price")
public class CurrentPrice {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "cur_seq_gen"
    )
    @SequenceGenerator(
            name = "cur_seq_gen",
            sequenceName = "cur_seq",
            allocationSize = 5
    )
    private Long id;

    @Column(name = "stock_name", nullable = false)
    private String stockName;

    @Column(name = "current_price", nullable = false)
    private Float currentPrice;
}
