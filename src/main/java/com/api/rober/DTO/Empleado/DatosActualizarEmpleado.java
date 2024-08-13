package com.api.rober.DTO.Empleado;

import com.api.rober.Models.Enum.EstadoCivil;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record DatosActualizarEmpleado(

        @NotNull
        Integer id,
        String nom,
        String ape,
        String correo,
        String telefono,
        EstadoCivil estadoCivil
) {
}
