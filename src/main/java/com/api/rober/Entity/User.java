package com.api.rober.Entity;

import com.api.rober.Controllers.DTO.User.DTORegistroUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuario")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_us;

    @Column(name = "us_nombre")
    private String name;
    @Column(name = "us_apellido")
    private String lastname;
    @Column(name = "us_correo", unique = true)
    private String email;
    @Column(name = "us_telefono", unique = true)
    private String phone;
    @Column(name = "us_estado")
    private boolean status;

    // clave foranea

    @ManyToOne
    @JoinColumn(name = "id_area")
    @JsonIgnore
    private Area area;

    // relacion

    @OneToMany(mappedBy = "userDoc", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnore

    private List<Document> documentList = new ArrayList<>();

    @OneToMany(mappedBy = "userRol", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnore
    private List<UsRol> usRolList = new ArrayList<>();


    // save
    public User(DTORegistroUser dtoRegistroUser) {
        this.status = true;
        this.name = dtoRegistroUser.name();
        this.lastname = dtoRegistroUser.lastname();
        this.email = dtoRegistroUser.email();
        this.phone = dtoRegistroUser.phone();
        this.area = dtoRegistroUser.area();
    }

    // delete logic
    public void desactivarUser() {
        this.status = false;
    }
}
