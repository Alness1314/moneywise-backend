package com.alness.moneywise.statement.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.alness.moneywise.purchase.entity.PurchaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "statement")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class StatementEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "uuid")
    private UUID id;

    @Column(nullable = false, columnDefinition = "character varying(64)")
    private String bank;

    @Column(columnDefinition = "character varying(64)")
    private String account;

    @Column(name = "statement_date", nullable = false, columnDefinition = "date")
    private LocalDate statementDate;

    @Column(name = "payment_date", nullable = false, columnDefinition = "date")
    private LocalDate paymentDate;

    @Column(nullable = false, columnDefinition = "numeric(21,8)")
    private BigDecimal payment;

    @Column(name = "payment_without_interest", nullable = false, columnDefinition = "numeric(21,8)")
    private BigDecimal paymentWithoutInterest;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "statement_purchase", joinColumns = @JoinColumn(name = "statement_id"), inverseJoinColumns = @JoinColumn(name = "purchase_id"), uniqueConstraints = {
    @UniqueConstraint(columnNames = { "statement_id", "purchase_id" }) })
    private List<PurchaseEntity> purchase;

    @Column(name = "create_at", nullable = false, columnDefinition = "timestamp without time zone")
    private LocalDateTime createAt;
}
