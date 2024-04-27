package com.api.rober.Controllers.DTO;


import com.api.rober.Entity.UsRol;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RolDTO {
    private Integer id_rol;

    private String code;
    private Date date;

    private BigDecimal income;
    private BigDecimal expenses;
    private BigDecimal subSalary;
    private List<UsRol> usRolList = new ArrayList<>();
}
