package com.api.rober.Controllers.DTO;

import com.api.rober.Entity.Rol;
import com.api.rober.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsRolDTO {

    private Integer idUsRol;
    private User userRol;
    private Rol rol;
}
