package com.api.rober.DTO.FichaPago;

import com.api.rober.Models.Enum.EstadoRol;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Date;

public record DatosRegistroFichaPago(

        @NotNull
        Integer idEmp,
        @NotBlank
        Date fechaEmision,
        @NotBlank
        BigDecimal ingresos,
        @NotBlank
        BigDecimal egresos,
        @NotBlank
        BigDecimal sueldo,
        @NotBlank
        BigDecimal sueldoNeto,
        @NotBlank
        EstadoRol estadoRol
) {
}
