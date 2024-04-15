package com.api.rober.DTO.DTODocument;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistroDocument(
        @NotBlank
        String doc_cedula,
        @NotBlank
        String doc_pasaporte,
        @NotBlank
        String doc_ruc
) {
}
