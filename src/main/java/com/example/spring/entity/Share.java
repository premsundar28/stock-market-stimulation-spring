package com.example.spring.entity;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "share")
@Data
@NoArgsConstructor
@Transactional
public class Share {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "share_name", nullable = false, length = 100, unique = true)
    private String shareName;

    @Column(name = "number_of_shares", nullable = false)
    private int numberOfShares;

    @Column(name = "bought_price", nullable = false)
    private float boughtPrice;

    @Column(name = "current_price", nullable = false)
    private float currentPrice;

    @Column(name = "returns", nullable = false)
    private float returns;

    @ManyToOne
    @JoinColumn(name = "demat_account_id", nullable = false)
    private DematAccount dematAccount; // Updated field to link to DematAccount
}
