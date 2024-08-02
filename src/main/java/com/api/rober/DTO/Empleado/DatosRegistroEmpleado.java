package com.api.rober.DTO.Empleado;


import com.api.rober.Models.Enum.EstadoCivil;
import jakarta.validation.constraints.NotBlank;

public record DatosRegistroEmpleado(
        @NotBlank
        String nom,
        @NotBlank
        String ape,
        @NotBlank
        String correo,
        @NotBlank
        String telefono,
        @NotBlank
        EstadoCivil estadoCivil
) {
}
