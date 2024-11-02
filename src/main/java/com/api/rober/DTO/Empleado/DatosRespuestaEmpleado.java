package com.api.rober.DTO.Empleado;

import com.api.rober.Models.Area;
import com.api.rober.Models.Documento;
import com.api.rober.Models.Enum.EstadoCivil;

public record DatosRespuestaEmpleado(
        Area idArea,
        Documento idDo,
        String nom,
        String ape,
        String correo,
        String telefono,
        EstadoCivil estadoCivil
) {
}
