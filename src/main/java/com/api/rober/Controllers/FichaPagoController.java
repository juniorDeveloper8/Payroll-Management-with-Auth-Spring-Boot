package com.api.rober.Controllers;

import com.api.rober.DTO.FichaPago.DatosActualizacionFichaPago;
import com.api.rober.DTO.FichaPago.DatosListaFichaPago;
import com.api.rober.DTO.FichaPago.DatosRegistroFichaPago;
import com.api.rober.DTO.FichaPago.DatosRespuestaFichaPago;
import com.api.rober.Interface.EmpleadoInterface;
import com.api.rober.Interface.FichaPagoInterface;
import com.api.rober.Models.FichaPago;
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
public class FichaPagoController {

    @Autowired
    private FichaPagoInterface fichaPagoInterface;

    @PostMapping
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
    public ResponseEntity<List<DatosListaFichaPago>> getFicha(
            @RequestParam String nombre) {

        List<FichaPago> fichas = fichaPagoInterface.findByEmpleadoNombre(nombre);

        List<DatosListaFichaPago> listaFichaPagos = fichas.stream()
                .map(DatosListaFichaPago::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(listaFichaPagos);
    }

    @PatchMapping("/{id}")
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



