package com.api.rober.DTO.DTOArea;

import com.api.rober.Entity.AreaEntity;

public record DatosListarArea(

        int id_ar,
        String ar_nom,
        String ar_des
) {
    public DatosListarArea(AreaEntity area) {
        this(
                area.getId_ar(),
                area.getAr_nom(),
                area.getAr_des()
        );

    }
}
