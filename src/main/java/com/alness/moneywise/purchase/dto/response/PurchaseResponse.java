package com.alness.moneywise.purchase.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

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
public class PurchaseResponse {
    private UUID id;
    private String store;
    private String description;
    private BigDecimal mount;
    private Integer deadlines;
    private Boolean withoutInterest;
    private BigDecimal monthlyPayment;
    private Boolean ownDebt;
    private LocalDate purchaseDate;
    private LocalDateTime createAt;
}
