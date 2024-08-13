package com.api.rober.Controllers;

import com.api.rober.DTO.Area.DatosListarArea;
import com.api.rober.DTO.Area.DatosRegistroArea;
import com.api.rober.DTO.Area.DatosRespuestaArea;
import com.api.rober.Interface.AreaInterface;
import com.api.rober.Models.Area;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("area")
@Tag(name = "Área", description = "Operaciones relacionadas con las áreas")
public class AreaController {

    @Autowired
    private AreaInterface areaInterface;

    @PostMapping
    @Operation(
            summary = "Crear una nueva área",
            description = "Permite crear una nueva área en el sistema.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Área creada exitosamente"),
                    @ApiResponse(responseCode = "400", description = "Solicitud incorrecta")
            }
    )
    public ResponseEntity<DatosRespuestaArea> createArea(@RequestBody DatosRegistroArea datosRegistroArea, UriComponentsBuilder uriComponentsBuilder) {
        Area area = new Area(datosRegistroArea);
        area = areaInterface.save(area);

        DatosRespuestaArea datosRespuestaArea = new DatosRespuestaArea(
                area.getId(),
                area.getNom(),
                area.getDescrip()
        );

        URI url = uriComponentsBuilder.path("/area/{id}").buildAndExpand(area.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaArea);
    }

    @GetMapping
    @Operation(
            summary = "Obtener todas las áreas",
            description = "Devuelve una lista de todas las áreas registradas en el sistema.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de áreas"),
                    @ApiResponse(responseCode = "204", description = "No se encontraron áreas")
            }
    )
    public ResponseEntity<List<DatosListarArea>> getAllAreas() {
        List<Area> areas = (List<Area>) areaInterface.findAll();

        List<DatosListarArea> listaAreas = areas.stream()
                .map(DatosListarArea::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(listaAreas);
    }
}
