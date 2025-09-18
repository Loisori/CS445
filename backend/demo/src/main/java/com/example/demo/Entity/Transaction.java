package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    private Double amount;
    private Double platformFee;
    private Double netAmount;

    private String paymentMethod;
    private String status;

    private LocalDateTime createdAt;
    private LocalDateTime capturedAt;
    @ManyToOne
    @JoinColumn(name = "Pledge_id",nullable = false)
    private pledge pledge;
}
