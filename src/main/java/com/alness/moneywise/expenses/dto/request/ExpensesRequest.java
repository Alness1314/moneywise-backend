package com.alness.moneywise.expenses.dto.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class ExpensesRequest {

    @NotNull
    private String description;

    @NotNull
    private BigDecimal amount;
    
    @NotNull
    private String user;
    
    @NotNull
    private String paymentDate;
    
    @NotNull
    private Boolean paymentStatus;
}
