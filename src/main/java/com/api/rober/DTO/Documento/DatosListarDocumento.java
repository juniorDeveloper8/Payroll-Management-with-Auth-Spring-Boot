package com.api.rober.DTO.Documento;

import com.api.rober.Models.Documento;
import com.api.rober.Models.Enum.TipoDoc;

public record DatosListarDocumento(
        Integer id,
        TipoDoc tipoDoc,
        String numero,
        String nacionalidad
) {
    public DatosListarDocumento(Documento documento) {
        this(
                documento.getId(),
                documento.getTipoDoc(),
                documento.getNumero(),
                documento.getNacionalidad().toString()
        );
    }
}
