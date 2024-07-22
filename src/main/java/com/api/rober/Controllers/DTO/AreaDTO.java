package com.api.rober.Controllers.DTO;

import com.api.rober.Entity.User;
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
public class AreaDTO {
    //private List<Product> productList = new ArrayList<>();

    private Integer id_ar;
    private String name;
    private String des;

}
