package com.api.rober.DTO.DTOContacto;

import com.api.rober.Entity.ContactoEntity;

public record DatosListarContacto(
        int id_con,
        String con_telefono
) {
    public DatosListarContacto(ContactoEntity con) {

        this(
                con.getId_con(),
                con.getCon_telefono()
        );
    }
}
