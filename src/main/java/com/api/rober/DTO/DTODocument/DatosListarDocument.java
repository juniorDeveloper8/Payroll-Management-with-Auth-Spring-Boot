package com.api.rober.DTO.DTODocument;

import com.api.rober.Entity.DocumentEntity;

public record DatosListarDocument(
        int id_doc,
        String doc_cedula,
        String doc_pasaporte,
        String doc_ruc
) {

    public DatosListarDocument(DocumentEntity doc) {
        this(
                doc.getId_doc(),
                doc.getDoc_cedula(),
                doc.getDoc_pasaporte(),
                doc.getDoc_ruc()
        );

    }
}
