package com.api.rober.DTO.Account;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistroAccount(
        @NotBlank
        String username,
        @NotBlank
        String password
) {
}
