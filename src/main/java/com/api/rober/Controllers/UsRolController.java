package com.api.rober.Controllers;

import com.api.rober.Controllers.DTO.UsRolDTO;
import com.api.rober.Entity.UsRol;
import com.api.rober.Services.IUsRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class UsRolController {

    @Autowired
    private IUsRolService usRolService;

    // me refiero al usuario mas, el rol de pago
    @GetMapping(value = "/Todo")
    public ResponseEntity<?> findAll() {
        List<UsRolDTO> usRolList = usRolService.findAll()
                .stream()
                .map(usRol -> UsRolDTO.builder()
                        .idUsRol(usRol.getIdUsRol())
                        .userRol(usRol.getUserRol())
                        .rol(usRol.getRol())
                        .build())
                .toList();
        return ResponseEntity.ok(usRolList);
    }
    /**
     * al guardar para q haya congruensia de datos toaca la parte de empleados aca
     * y lo mismo con los roles
     */

    @PostMapping("Todo")
    public ResponseEntity<?> save(@RequestBody UsRolDTO usRolDTO) throws URISyntaxException  {

        UsRol usRol = UsRol.builder()
                .userRol(usRolDTO.getUserRol())
                .rol(usRolDTO.getRol())
                .build();
        usRolService.save(usRol);
        return ResponseEntity.created(new URI("/api/Todo")).build();
    }
}
