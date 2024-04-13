package com.api.rober.DTO.DTOUser;

import com.api.rober.Entity.AreaEntity;
import com.api.rober.Entity.DocumentEntity;
import jakarta.validation.constraints.NotBlank;

public record DatosRegistroUser(
        @NotBlank
        String us_nombre,
        @NotBlank
        String us_apellido,
        @NotBlank
        String us_correo,
        @NotBlank
        DocumentEntity documentEntity,
        @NotBlank
        AreaEntity areaEntity

) {
}
