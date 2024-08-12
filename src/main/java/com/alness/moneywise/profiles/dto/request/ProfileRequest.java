package com.alness.moneywise.profiles.dto.request;

import jakarta.validation.constraints.Pattern;
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
public class ProfileRequest {

    @Pattern(regexp = "^(Administrator|User|Employee)$", message = "The name field can only have the values: Administrator, User, or Employee.")
    private String name;

}
