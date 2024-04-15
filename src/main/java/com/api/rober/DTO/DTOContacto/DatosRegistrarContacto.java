package com.api.rober.DTO.DTOContacto;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistrarContacto(
        @NotBlank
        String con_telefono
) {
}
