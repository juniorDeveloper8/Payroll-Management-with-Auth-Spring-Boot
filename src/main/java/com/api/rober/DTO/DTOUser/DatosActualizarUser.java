package com.api.rober.DTO.DTOUser;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarUser(
        @NotNull
        int id_us,
        String us_nombre,
        String us_apellido,
        String us_correo
) {
}
