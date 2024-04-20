package com.api.rober.Controllers.DTO;

import com.api.rober.Entity.Area;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private Integer id_us;
    private String name;
    private String lastname;
    private String email;
    private String psw;
    private String phone;
    private Boolean status;
    // clave foranea
    private Area area;
}
