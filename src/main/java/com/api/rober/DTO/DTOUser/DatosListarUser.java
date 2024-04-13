package com.api.rober.DTO.DTOUser;

import com.api.rober.Entity.AreaEntity;
import com.api.rober.Entity.DocumentEntity;
import com.api.rober.Entity.UserEntity;

public record DatosListarUser(

        int id_us,
        String us_nombre,
        String us_apellido,
        String us_correo,
        DocumentEntity documentEntity,
        AreaEntity areaEntity
) {
    public DatosListarUser(UserEntity user) {
        this(
                user.getId_us(),
                user.getUs_nombre(),
                user.getUs_apellido(),
                user.getUs_correo(),
                user.getDocumentEntity(),
                user.getAreaEntity()
        );
    }
}
