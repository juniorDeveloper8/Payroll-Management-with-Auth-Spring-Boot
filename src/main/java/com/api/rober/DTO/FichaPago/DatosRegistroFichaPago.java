package com.api.rober.DTO.FichaPago;

import com.api.rober.Models.Enum.EstadoRol;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.util.Date;

public record DatosRegistroFichaPago(

        @NotBlank
        Date fechaEmision,
        @NotBlank
        BigDecimal ingresos,
        @NotBlank
        BigDecimal egresos,
        @NotBlank
        BigDecimal sueldo,
        @NotBlank
        EstadoRol estadoRol
) {
}
