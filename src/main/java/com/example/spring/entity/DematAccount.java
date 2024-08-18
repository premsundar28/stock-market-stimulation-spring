package com.example.spring.entity;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "demat_account")
@Data
@NoArgsConstructor
@Transactional
public class DematAccount {

    @Id
    @Column(name = "demat_number", unique = true, nullable = false, length = 20)
    @GeneratedValue(generator = "demat-number-generator")
    @GenericGenerator(
            name = "demat-number-generator",
            strategy = "com.example.spring.generator.AccountNumberGenerator"
    )
    private String dematNumber;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User accountHolder;

    @OneToMany(mappedBy = "dematAccount", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Share> shares = new ArrayList<>();
}
