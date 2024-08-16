package com.alness.moneywise.purchase.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.alness.moneywise.statement.entity.StatementEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "purchases")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class PurchaseEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "uuid")
    private UUID id;
    private String store;
    private String description;
    private BigDecimal mount;
    private String deadlines;
    private Boolean withoutInterest;
    private String remainingDeadlines;
    private Boolean ownDebt;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "statement_id")
    private StatementEntity statement;
    private LocalDateTime createAt;
}
