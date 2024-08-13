package com.api.rober.DTO.FichaPago;

import com.api.rober.Models.Enum.EstadoRol;

import java.math.BigDecimal;
import java.util.Date;

public record DatosRespuestaFichaPago(
        Date fechaEmision,
        BigDecimal ingresos,
        BigDecimal egresos,
        BigDecimal sueldo,
        EstadoRol estadoRol
) {
}
