package com.api.rober.Controllers;

import com.api.rober.DTO.Empleado.DatosActualizarEmpleado;
import com.api.rober.DTO.Empleado.DatosListaEmpleados;
import com.api.rober.DTO.Empleado.DatosRegistroEmpleado;
import com.api.rober.DTO.Empleado.DatosRespuestaEmpleado;
import com.api.rober.Interface.AreaInterface;
import com.api.rober.Interface.EmpleadoInterface;
import com.api.rober.Models.Empleado;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("empleados")
@Tag(name = "Empleado", description = "Operaciones relacionadas con empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoInterface empleadoInterface;

    @Autowired
    private AreaInterface areaInterface;

    @PostMapping
    @Operation(summary = "Guardar un nuevo empleado",
            description = "Guarda un nuevo empleado en la base de datos y devuelve los datos del empleado guardado.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Empleado creado exitosamente"),
                    @ApiResponse(responseCode = "400", description = "Solicitud inválida")
            })
    public ResponseEntity<DatosRespuestaEmpleado> saveEmpleado(@RequestBody DatosRegistroEmpleado datosRegistroEmpleado, UriComponentsBuilder uriComponentsBuilder) {
        if (datosRegistroEmpleado.nom().isBlank() || datosRegistroEmpleado.ape().isBlank() || datosRegistroEmpleado.correo().isBlank() || datosRegistroEmpleado.estadoCivil() == null) {
            return ResponseEntity.badRequest().build();
        }

        Empleado empleado = empleadoInterface.save(new Empleado(datosRegistroEmpleado));
        DatosRespuestaEmpleado datosRespuestaEmpleado = new DatosRespuestaEmpleado(
                empleado.getNom(),
                empleado.getApe(),
                empleado.getCorreo(),
                empleado.getTelefono(),
                empleado.getEstadoCivil()
        );
        URI url = uriComponentsBuilder.path("/empleados/{id}").buildAndExpand(empleado.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaEmpleado);
    }

    @GetMapping
    @Operation(summary = "Obtener todos los empleados",
            description = "Devuelve una lista de todos los empleados activos.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de empleados recuperada exitosamente")
            })
    public ResponseEntity<?> getEmpleados() {

        List<DatosListaEmpleados> listaEmpleados = empleadoInterface.findByActivoTrue().stream()

                .map(empleado -> DatosListaEmpleados.builder()

                        .id(empleado.getId())
                        .nom(empleado.getNom())
                        .ape(empleado.getApe())
                        .correo(empleado.getCorreo())
                        .telefono(empleado.getTelefono())
                        .estadoCivil(empleado.getEstadoCivil())
                        .build())
                .collect(Collectors.toList());

        return ResponseEntity.ok(listaEmpleados);

    }

    @GetMapping("/porArea")
    @Operation(summary = "Obtener empleados por área",
            description = "Devuelve una lista de empleados que pertenecen a una área específica.",
            parameters = @Parameter(name = "areaName", description = "Nombre del área para filtrar empleados"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de empleados recuperada exitosamente"),
                    @ApiResponse(responseCode = "204", description = "No hay empleados para el área especificada")
            })
    public ResponseEntity<List<Empleado>> getEmpleadosPorArea(
            @RequestParam String areaName) {

        List<Empleado> empleados = areaInterface.findByNom(areaName);

        if (empleados.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(empleados);
    }

    @PatchMapping("/{id}")
    @Transactional
    @Operation(summary = "Actualizar un empleado",
            description = "Actualiza los datos de un empleado existente.",
            parameters = @Parameter(name = "id", description = "ID del empleado a actualizar"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Empleado actualizado exitosamente"),
                    @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
                    @ApiResponse(responseCode = "404", description = "Empleado no encontrado")
            })
    public ResponseEntity<DatosRespuestaEmpleado> updateEmpleado(@RequestBody DatosActualizarEmpleado actualizarEmpleado, @RequestParam Integer id) {

        if (actualizarEmpleado.id() == null || actualizarEmpleado.nom().isBlank() || actualizarEmpleado.ape().isBlank() || actualizarEmpleado.correo().isBlank() || actualizarEmpleado.telefono().isBlank() || actualizarEmpleado.estadoCivil() == null) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Empleado> optionalEmpleado = empleadoInterface.findById(id);

        if (optionalEmpleado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Empleado empleado = optionalEmpleado.get();

        empleado.setNom(actualizarEmpleado.nom());
        empleado.setApe(actualizarEmpleado.ape());
        empleado.setCorreo(actualizarEmpleado.correo());
        empleado.setTelefono(actualizarEmpleado.telefono());
        empleado.setEstadoCivil(actualizarEmpleado.estadoCivil());

        var datosEmpleado = new DatosRespuestaEmpleado(
                empleado.getNom(),
                empleado.getApe(),
                empleado.getCorreo(),
                empleado.getTelefono(),
                empleado.getEstadoCivil()
        );
        return ResponseEntity.ok(datosEmpleado);
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un empleado",
            description = "Elimina un empleado de la base de datos.",
            parameters = @Parameter(name = "id", description = "ID del empleado a eliminar"),
            responses = {
                    @ApiResponse(responseCode = "204", description = "Empleado eliminado exitosamente"),
                    @ApiResponse(responseCode = "404", description = "Empleado no encontrado")
            })
    public ResponseEntity<?> deleteEmpleado(@RequestParam Integer id) {

        Optional<Empleado> optionalEmpleado = empleadoInterface.findById(id);

        if (optionalEmpleado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        empleadoInterface.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
