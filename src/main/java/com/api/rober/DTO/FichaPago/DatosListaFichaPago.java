package com.api.rober.DTO.FichaPago;

import com.api.rober.Models.Enum.EstadoRol;
import com.api.rober.Models.FichaPago;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.Date;
@Builder
public record DatosListaFichaPago(
        Integer id,
        Date fechaEmision,
        BigDecimal ingresos,
        BigDecimal egresos,
        BigDecimal sueldo,
        String estadoRol
) {

    public DatosListaFichaPago(FichaPago fichaPago) {
        this(
                fichaPago.getId(),
                fichaPago.getFechaEmision(),
                fichaPago.getIngresos(),
                fichaPago.getEgresos(),
                fichaPago.getSueldo(),
                fichaPago.getEstadoRol().toString()
        );
    }
}
