package com.api.rober.DTO.Empleado;

import com.api.rober.Models.Enum.EstadoCivil;

public record DatosRespuestaEmpleado(
        String nom,
        String ape,
        String correo,
        String telefono,
        EstadoCivil estadoCivil
) {
}
