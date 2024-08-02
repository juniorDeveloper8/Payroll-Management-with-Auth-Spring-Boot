package com.api.rober.DTO.FichaPago;

import java.math.BigDecimal;
import java.util.Date;

public record DatosRespuestaFichaPago(
        Date fechaEmision,
        BigDecimal ingresos,
        BigDecimal egresos,
        BigDecimal sueldo,
        String estadoRol
) {
}
