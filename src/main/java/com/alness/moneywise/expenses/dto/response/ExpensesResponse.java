package com.alness.moneywise.expenses.dto.response;

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
public class ExpensesResponse {
    private UUID id;
    private String description;
    private BigDecimal amount;
    private LocalDate paymentDate;
    private Boolean paymentStatus;
    private LocalDateTime createAt;
}
