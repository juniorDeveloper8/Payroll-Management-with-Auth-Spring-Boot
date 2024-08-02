package com.api.rober.DTO.Documento;

import com.api.rober.Models.Enum.Nacionalidad;
import com.api.rober.Models.Enum.TipoDoc;

public record DatosRespuestaDocumento(
        TipoDoc tipoDoc,
        String numero,
        Nacionalidad nacionalidad
) {
}
