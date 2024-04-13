package com.api.rober.DTO.DTOUser;

import com.api.rober.Entity.AreaEntity;
import com.api.rober.Entity.DocumentEntity;

public record DatosRespuestaUser(

        int id_us,
        String us_nombre,
        String us_apellido,
        String us_correo,
        DocumentEntity documentEntity,
        AreaEntity areaEntity
) {
}
