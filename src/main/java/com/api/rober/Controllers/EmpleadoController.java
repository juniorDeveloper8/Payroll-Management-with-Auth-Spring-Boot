package com.api.rober.Controllers;

import com.api.rober.DTO.Empleado.DatosActualizarEmpleado;
import com.api.rober.DTO.Empleado.DatosListaEmpleados;
import com.api.rober.DTO.Empleado.DatosRegistroEmpleado;
import com.api.rober.DTO.Empleado.DatosRespuestaEmpleado;
import com.api.rober.Interface.EmpleadoInterface;
import com.api.rober.Models.Empleado;
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
public class EmpleadoController {

    @Autowired
    private EmpleadoInterface empleadoInterface;

    @PostMapping
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

    @PatchMapping("/{id}")
    @Transactional
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
    public ResponseEntity<?> deleteEmpleado(@RequestParam Integer id) {

        Optional<Empleado> optionalEmpleado = empleadoInterface.findById(id);

        if (optionalEmpleado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        empleadoInterface.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
