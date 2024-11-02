package com.api.rober.DTO.FichaPago;

import com.api.rober.Models.Enum.EstadoRol;
import com.api.rober.Models.FichaPago;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.Date;
@Builder
public record DatosListaFichaPago(
        Integer id,
        String codigo,
        Date fechaEmision,
        BigDecimal ingresos,
        BigDecimal egresos,
        BigDecimal sueldo,
        BigDecimal sueldoNeto,
        String estadoRol
) {

    public DatosListaFichaPago(FichaPago fichaPago) {
        this(
                fichaPago.getId(),
                fichaPago.getCodigo(),
                fichaPago.getFechaEmision(),
                fichaPago.getIngresos(),
                fichaPago.getEgresos(),
                fichaPago.getSueldo(),
                fichaPago.getSueldoNeto(),
                fichaPago.getEstadoRol().toString()
        );
    }
}
