package com.alness.moneywise.users.dto.request;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
public class UserRequest {
    @Email
    private String username;

    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&#])[A-Za-z\\d@$!%*?&#]{8,}$", message = "La contraseña debe tener al menos 8 caracteres, incluir un número, una letra mayúscula y un carácter especial (@, $, !, %, *, ?, &, #).")
    private String password;

    @NotNull(message = "El objeto detalle de usuario no puede ser nulo.")
    @Valid
    private DetailRequest detailUser;

    @Valid
    @Size(min = 1, message = "La lista de perfiles no puede estar vacía.")
    private List<@NotBlank(message = "El perfil no puede estar vacío.") @Pattern(regexp = "^(Administrator|User|Employee)$", message = "El campo de perfil solo puede tener los valores: Administrator, User, o Employee.") String> profiles;
}
