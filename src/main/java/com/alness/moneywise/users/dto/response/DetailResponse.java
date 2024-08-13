package com.alness.moneywise.users.dto.response;

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
public class DetailResponse {
    private UUID id;
    private String names;
    private String lastname;
    private String maternalSurname;
}
