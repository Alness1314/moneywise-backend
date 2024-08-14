package com.alness.moneywise.expenses.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import com.alness.moneywise.users.entity.UserEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
@Entity
@Table(name = "expenses")
public class ExpensesEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "uuid")
    private UUID id;

    @Column(name = "bank_or_entity", nullable = false, columnDefinition = "character varying(64)")
    private String bankOrEntity;

    @Column(nullable = false, columnDefinition = "character varying(256)")
    private String description;

    @Column(nullable = false, columnDefinition = "numeric(21,8)")
    private BigDecimal amount;

    @Column(name = "payment_date", nullable = false, columnDefinition = "date")
    private LocalDate paymentDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity usuario;

    @Column(name = "payment_status", nullable = false, columnDefinition = "boolean")
    private Boolean paymentStatus;

    @Column(name = "create_at", nullable = false, columnDefinition = "timestamp without time zone")
    private LocalDateTime createAt;

    @PrePersist()
    public void init(){
        setCreateAt(LocalDateTime.now());
    }
}
