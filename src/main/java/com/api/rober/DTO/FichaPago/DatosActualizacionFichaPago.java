package com.api.rober.DTO.FichaPago;

import com.api.rober.Models.Enum.EstadoRol;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Date;

public record DatosActualizacionFichaPago(

        @NotNull
        Integer id,
        Date fechaEmision,
        BigDecimal ingresos,
        BigDecimal egresos,
        BigDecimal sueldo,
        EstadoRol estadoRol
) {
}
