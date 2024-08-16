package com.alness.moneywise.statement.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.alness.moneywise.purchase.entity.PurchaseEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

public class StatementEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "uuid")
    private UUID id;

    private String bank;

    private String account;

    private String statementDate;

    private String paymentDate;

    private BigDecimal payment;

    private BigDecimal paymentWithoutInterest;

    @OneToMany(mappedBy = "statement", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PurchaseEntity> purchase;

    @Column(name = "create_at", nullable = false, columnDefinition = "timestamp without time zone")
    private LocalDateTime createAt;
}
