package com.api.rober.Models;

import com.api.rober.DTO.Empleado.DatosActualizarEmpleado;
import com.api.rober.DTO.Empleado.DatosRegistroEmpleado;
import com.api.rober.Models.Enum.EstadoCivil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "empleado")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_emp", unique = true, nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false)
    private String nom;

    @Column(name = "apellido", nullable = false)
    private String ape;

    @Column(unique = true, nullable = false)
    private String correo;

    @Column(unique = true, nullable = false)
    private String telefono;

    @Column(columnDefinition = "TINYINT(1)", nullable = false)
    private boolean activo;

    @Enumerated(EnumType.STRING)
    private EstadoCivil estadoCivil;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_area")
    private Area area;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_do")
    private Documento documento;

    //lista de roles por empleado
    @OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnore
    private List<FichaPago> fichaPagoList = new ArrayList<>();

    //Metodos Crud

    public Empleado(DatosRegistroEmpleado datosRegistroEmpleado) {
        this.activo = true;
        this.nom = datosRegistroEmpleado.nom();
        this.ape = datosRegistroEmpleado.ape();
        this.correo = datosRegistroEmpleado.correo();
        this.telefono = datosRegistroEmpleado.telefono();
        this.estadoCivil = datosRegistroEmpleado.estadoCivil();
    }

    public void update(DatosActualizarEmpleado datosActualizarEmpleado) {
        if (datosActualizarEmpleado == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El objeto DatosActualizarEmpleado no puede ser null.");
        }
        // Actualizar solo los campos no nulos
        if (datosActualizarEmpleado.nom() != null) {
            this.nom = datosActualizarEmpleado.nom();
        }
        if (datosActualizarEmpleado.ape() != null) {
            this.ape = datosActualizarEmpleado.ape();
        }
        if (datosActualizarEmpleado.correo() != null) {
            this.correo = datosActualizarEmpleado.correo();
        }
        if (datosActualizarEmpleado.telefono() != null) {
            this.telefono = datosActualizarEmpleado.telefono();
        }
        if (datosActualizarEmpleado.estadoCivil() != null) {
            this.estadoCivil = datosActualizarEmpleado.estadoCivil();
        }

    }

    public void delete() {
        this.activo = false;
    }

}
