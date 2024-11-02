package com.api.rober.DTO.FichaPago;

import com.api.rober.Models.Empleado;
import com.api.rober.Models.Enum.EstadoRol;

import java.math.BigDecimal;
import java.util.Date;

public record DatosRespuestaFichaPago(
        Empleado id_emp,
        Date fechaEmision,
        BigDecimal ingresos,
        BigDecimal egresos,
        BigDecimal sueldo,
        BigDecimal sueldoNeto,
        EstadoRol estadoRol
) {
}
