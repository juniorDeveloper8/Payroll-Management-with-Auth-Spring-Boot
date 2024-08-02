package com.api.rober.DTO.Documento;

import com.api.rober.Models.Enum.Nacionalidad;
import com.api.rober.Models.Enum.TipoDoc;
import jakarta.validation.constraints.NotBlank;

public record DatosRegistroDocumento(

        @NotBlank
        TipoDoc tipoDoc,
        @NotBlank
        String numero,
        @NotBlank
        Nacionalidad nacionalidad
) {
}
