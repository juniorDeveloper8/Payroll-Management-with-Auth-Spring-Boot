package com.api.rober.DTO.DTORol;

import com.api.rober.Entity.RolEntity;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public record DatosListarRol(
        int id_rol,
        String codigo,
        Date fecha,
        float rol_ingresos,
        float rol_egresos,
        float rol_sueldo
) {

    public DatosListarRol(RolEntity rol) {
        this(
                rol.getId_rol(),
                rol.getCodigo(),
                rol.getFecha(),
                rol.getRol_ingresos(),
                rol.getRol_egresos(),
                rol.getRol_sueldo()
        );
    }
}
