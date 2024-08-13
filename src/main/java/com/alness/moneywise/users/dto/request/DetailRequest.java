package com.alness.moneywise.users.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
public class DetailRequest {
    @NotNull(message = "El nombre no puede ser nulo")
    @NotEmpty(message = "El nombre no puede estar vacío")
    private String names;

    @NotNull(message = "El apellido paterno no puede ser nulo")
    @NotEmpty(message = "El apellido paterno no puede estar vacío")
    private String lastname;

    private String maternalSurname;
}
