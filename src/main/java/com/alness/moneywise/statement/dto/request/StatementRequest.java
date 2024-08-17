package com.alness.moneywise.statement.dto.request;

import java.math.BigDecimal;
import java.util.List;

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
public class StatementRequest {
    private String bank;
    private String account;
    private String statementDate;
    private String paymentDate;
    private BigDecimal payment;
    private BigDecimal paymentWithoutInterest;
    private List<String> purchase;
}
