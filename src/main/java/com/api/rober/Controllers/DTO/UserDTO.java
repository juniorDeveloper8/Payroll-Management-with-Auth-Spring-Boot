package com.api.rober.Controllers.DTO;

import com.api.rober.Entity.Area;
import com.api.rober.Entity.Document;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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
    private int status;
    // clave foranea
    private Area area;
    private List<Document> documentList = new ArrayList<>();
}
