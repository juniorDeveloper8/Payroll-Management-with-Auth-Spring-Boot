package com.api.rober.Controllers.DTO;

import com.api.rober.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DocumentDTO {

    private Integer id_doc;
    private String icard;
    private String passport;
    private String ruc;

    private User userDoc;
}
