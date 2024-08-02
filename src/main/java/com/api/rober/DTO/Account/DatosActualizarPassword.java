package com.api.rober.DTO.Account;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarPassword(
        @NotNull
        Integer id,
        String password
) {
}
