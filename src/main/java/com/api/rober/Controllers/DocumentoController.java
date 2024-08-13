package com.api.rober.Controllers;

import com.api.rober.DTO.Documento.DatosListarDocumento;
import com.api.rober.DTO.Documento.DatosRegistroDocumento;
import com.api.rober.DTO.Documento.DatosRespuestaDocumento;
import com.api.rober.Interface.DocumentoInterface;
import com.api.rober.Models.Documento;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("documento")
@Tag(name = "Documento", description = "Operaciones relacionadas con documentos")
public class DocumentoController {

    @Autowired
    private DocumentoInterface documentoInterface;

    @PostMapping
    @Operation(summary = "Guardar un nuevo documento",
            description = "Guarda un nuevo documento en la base de datos y devuelve los datos del documento guardado.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Documento creado exitosamente"),
                    @ApiResponse(responseCode = "400", description = "Solicitud inválida")
            })
    public ResponseEntity<DatosRespuestaDocumento> saveDocumento(
            @RequestBody DatosRegistroDocumento datosRegistroDocumento) {

        if (datosRegistroDocumento.tipoDoc() == null ||
                datosRegistroDocumento.numero().isBlank() ||
                datosRegistroDocumento.nacionalidad() == null) {
            return ResponseEntity.badRequest().build();
        }

        Documento documento = new Documento(datosRegistroDocumento);
        documento = documentoInterface.save(documento);

        DatosRespuestaDocumento datosRespuestaDocumento = new DatosRespuestaDocumento(
                documento.getTipoDoc(),
                documento.getNumero(),
                documento.getNacionalidad()
        );

        return ResponseEntity.status(201).body(datosRespuestaDocumento);
    }

    @GetMapping
    @Operation(summary = "Obtener documentos por número",
            description = "Devuelve una lista de documentos asociados al número proporcionado.",
            parameters = @Parameter(name = "numero", description = "Número del documento para filtrar"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de documentos recuperada exitosamente"),
                    @ApiResponse(responseCode = "204", description = "No hay documentos para el número especificado")
            })
    public ResponseEntity<List<DatosListarDocumento>> getDocumentosPorNumero(
            @RequestParam String numero) {
        List<Documento> documentos = documentoInterface.findByNumero(numero);
        if (documentos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<DatosListarDocumento> listaDocumentos = documentos.stream()
                .map(DatosListarDocumento::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaDocumentos);
    }
}