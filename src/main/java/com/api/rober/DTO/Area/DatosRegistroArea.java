package com.api.rober.DTO.Area;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistroArea(
        @NotBlank
        String nom,
        @NotBlank
        String descrip
) {
}
