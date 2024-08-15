package com.alness.moneywise.income.dto.request;

import java.math.BigDecimal;

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
public class IncomeRequest {
    private String source;
    private String description;
    private BigDecimal amount;
    private String paymentDate;
    private String user;
}
