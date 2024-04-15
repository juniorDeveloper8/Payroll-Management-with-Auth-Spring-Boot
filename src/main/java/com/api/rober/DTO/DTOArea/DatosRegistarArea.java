package com.api.rober.DTO.DTOArea;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistarArea(
        @NotBlank
        String ar_nom,
        @NotBlank
        String ar_des
) {
}
