package com.shiyu.Pfinance.Entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String source;

    private BigDecimal amount;

    private LocalDate dateReceived;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


}
