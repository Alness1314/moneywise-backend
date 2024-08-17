package com.alness.moneywise.statement.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

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

    @Column(nullable = false, columnDefinition = "character varying(64)")
    private String store;

    @Column(columnDefinition = "character varying(128)")
    private String description;

    @Column(nullable = false, columnDefinition = "numeric(21,8)")
    private BigDecimal mount;

    @Column(nullable = false, columnDefinition = "integer")
    private Integer deadlines;

    @Column(name = "without_interest", nullable = false, columnDefinition = "boolean")
    private Boolean withoutInterest;

    @Column(name = "monthly_payment", nullable = false, columnDefinition = "integer")
    private BigDecimal monthlyPayment;

    @Column(name = "own_debt", nullable = false, columnDefinition = "boolean")
    private Boolean ownDebt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "statement_id")
    private StatementEntity statement;
    
    @Column(name =  "create_at", nullable = false, updatable = false, columnDefinition = "timestamp without time zone")
    private LocalDateTime createAt;
}
