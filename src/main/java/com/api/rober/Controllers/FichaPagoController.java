package com.api.rober.Controllers;

import com.api.rober.DTO.FichaPago.DatosActualizacionFichaPago;
import com.api.rober.DTO.FichaPago.DatosListaFichaPago;
import com.api.rober.DTO.FichaPago.DatosRegistroFichaPago;
import com.api.rober.DTO.FichaPago.DatosRespuestaFichaPago;
import com.api.rober.Interface.FichaPagoInterface;
import com.api.rober.Models.FichaPago;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("fichaPago")
@Tag(name = "FichaPago", description = "Operaciones relacionadas con fichas de pago")
public class FichaPagoController {

    @Autowired
    private FichaPagoInterface fichaPagoInterface;

    @PostMapping
    @Operation(summary = "Guardar una nueva ficha de pago",
            description = "Guarda una nueva ficha de pago en la base de datos y devuelve los datos de la ficha de pago guardada.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Ficha de pago creada exitosamente"),
                    @ApiResponse(responseCode = "400", description = "Solicitud inválida")
            })
    public ResponseEntity<DatosRespuestaFichaPago> saveFicha(@RequestBody DatosRegistroFichaPago registroFichaPago, UriComponentsBuilder uriComponentsBuilder) {

        if (registroFichaPago.fechaEmision() == null ||
                registroFichaPago.ingresos() == null ||
                registroFichaPago.egresos() == null ||
                registroFichaPago.sueldo() == null ||
                registroFichaPago.estadoRol() == null) {
            return ResponseEntity.badRequest().build();
        }

        FichaPago fichaPago = new FichaPago(registroFichaPago);
        fichaPago = fichaPagoInterface.save(fichaPago);

        DatosRespuestaFichaPago datosRespuestaFichaPago = new DatosRespuestaFichaPago(
                fichaPago.getFechaEmision(),
                fichaPago.getIngresos(),
                fichaPago.getEgresos(),
                fichaPago.getSueldo(),
                fichaPago.getEstadoRol()
        );

        URI url = uriComponentsBuilder.path("/fichaPago/{id}").buildAndExpand(fichaPago.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaFichaPago);
    }

    @GetMapping("/{nombre}")
    @Operation(summary = "Obtener fichas de pago por nombre de empleado",
            description = "Devuelve una lista de fichas de pago asociadas al nombre de un empleado.",
            parameters = @Parameter(name = "nombre", description = "Nombre del empleado para filtrar fichas de pago"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de fichas de pago recuperada exitosamente"),
                    @ApiResponse(responseCode = "204", description = "No hay fichas de pago para el nombre de empleado especificado")
            })
    public ResponseEntity<List<DatosListaFichaPago>> getFicha(
            @RequestParam String nombre) {

        List<FichaPago> fichas = fichaPagoInterface.findByEmpleadoNom(nombre);

        List<DatosListaFichaPago> listaFichaPagos = fichas.stream()
                .map(DatosListaFichaPago::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(listaFichaPagos);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Actualizar una ficha de pago",
            description = "Actualiza los datos de una ficha de pago existente.",
            parameters = {
                    @Parameter(name = "id", description = "ID de la ficha de pago a actualizar"),
                    @Parameter(name = "datosActualizacion", description = "Datos de actualización de la ficha de pago")
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ficha de pago actualizada exitosamente"),
                    @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
                    @ApiResponse(responseCode = "404", description = "Ficha de pago no encontrada")
            })
    public ResponseEntity<?> updateFicha(
            @RequestParam Integer id,
            @RequestBody DatosActualizacionFichaPago datosActualizacion) {

        Optional<FichaPago> fichaOptional = fichaPagoInterface.findById(id);

        if (fichaOptional.isPresent()) {
            FichaPago ficha = fichaOptional.get();
            ficha.update(datosActualizacion);
            fichaPagoInterface.save(ficha);
            return ResponseEntity.ok(ficha);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}



