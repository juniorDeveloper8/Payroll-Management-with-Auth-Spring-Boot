package com.api.rober.DTO.Area;

import com.api.rober.Models.Area;

public record DatosListarArea(
        Integer id,
        String nom,
        String descrip
) {

    public DatosListarArea(Area area) {
        this(
                area.getId(),
                area.getNom(),
                area.getDescrip().toString()
        );
    }
}
