package com.api.rober.DTO.Empleado;


import com.api.rober.Models.Enum.EstadoCivil;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroEmpleado(
        @NotNull
        Integer idDo,
        @NotNull
        Integer idArea,
        @NotBlank(message = "Nombre no debe estar vasio")
        String nom,
        @NotBlank(message = "Apellido no debe estar vasio")
        String ape,
        @NotBlank(message = "Correo no debe estar vasio")
        @Email(message = "Correo debe ser una dirección de correo electrónico válida")
        String correo,
        @NotBlank(message = "teleforno no debe estar vasio")
        String telefono,
        @NotBlank(message = "estado civil no debe estar vasio")
        EstadoCivil estadoCivil

) {
}
