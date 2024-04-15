package com.api.rober.DTO.DTOArea;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarArea(
        @NotNull
        int id_ar,
        String ar_nom,
        String ar_des
) {
}
