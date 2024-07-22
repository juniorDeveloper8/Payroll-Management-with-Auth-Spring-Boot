package com.api.rober.Controllers.DTO.User;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistroUserLogin(
        @NotBlank
        String login,
        @NotBlank
        String clave
) {
}
