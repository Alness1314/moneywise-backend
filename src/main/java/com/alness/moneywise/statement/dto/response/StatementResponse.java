package com.alness.moneywise.statement.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.alness.moneywise.purchase.dto.response.PurchaseResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatementResponse {
    private UUID id;
    private String bank;
    private String account;
    private LocalDate statementDate;
    private LocalDate paymentDate;
    private BigDecimal payment;
    private BigDecimal paymentWithoutInterest;
    private List<PurchaseResponse> purchase;
    private LocalDateTime createAt;
}
