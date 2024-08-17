package com.alness.moneywise.purchase.dto.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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

    @Min(1)
    @Max(24)
    private Integer deadlines;
    
    private Boolean withoutInterest;
    private Boolean ownDebt;
    private String purchaseDate;
}
