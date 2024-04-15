package com.api.rober.DTO.DTORol;

import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public record DatosRegistroRol(
        @NotBlank
        String codigo,
        @NotBlank
        Date fecha,
        @NotBlank
        float rol_ingresos,
        @NotBlank
        float rol_egresos,
        @NotBlank
        float rol_sueldo
) {
}
