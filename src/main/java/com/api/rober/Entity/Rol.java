package com.api.rober.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rol")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_rol;

    @Column(name = "codigo", unique = true, length = 255)
    private String code;
    @Column(name = "fecha")
    private Date date;

    @Column(name = "rol_ingresos")
    private BigDecimal income;
    @Column(name = "rol_egresos")
    private BigDecimal expenses;
    @Column(name = "rol_sueldo")
    private BigDecimal salary;

//    relacion

    @OneToMany(mappedBy = "rol", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnore
    private List<UsRol> usRolList = new ArrayList<>();

}
