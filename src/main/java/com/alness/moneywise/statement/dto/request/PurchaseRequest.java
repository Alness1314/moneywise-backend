package com.alness.moneywise.statement.dto.request;

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
public class PurchaseRequest {
    private String store;
    private String description;
    private BigDecimal mount;
    private Integer deadlines;
    private Boolean withoutInterest;
    private Integer remainingDeadlines;
    private Boolean ownDebt;
}
