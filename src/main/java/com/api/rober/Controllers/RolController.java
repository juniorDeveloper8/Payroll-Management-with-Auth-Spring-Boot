package com.api.rober.Controllers;

import com.api.rober.Controllers.DTO.RolDTO;
import com.api.rober.Entity.Rol;
import com.api.rober.Services.IRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class RolController {

    @Autowired
    private IRolService rolService;

    @PostMapping("/rol")
    public ResponseEntity<?> save(@RequestBody RolDTO rolDTO) throws URISyntaxException {

        if (rolDTO.getExpenses() == null || rolDTO.getIncome() == null || rolDTO.getSubSalary() == null) {
            return ResponseEntity.badRequest().build();
        }
        Rol rol = Rol.builder()
                .income(rolDTO.getIncome())
                .expenses(rolDTO.getExpenses())
                .subSalary(rolDTO.getSubSalary())
                .code(rolDTO.getCode())
                .date(rolDTO.getDate())
                .usRolList(rolDTO.getUsRolList())
                .build();
        rolService.save(rol);
        return ResponseEntity.created(new URI("/api/rol")).build();
    }

    @PutMapping("/rol/{id}")
    public ResponseEntity<?> updateRol(@PathVariable Integer id, @RequestBody RolDTO rolDTO) {
        Optional<Rol> rolOptional = rolService.findById(id);

        if (rolOptional.isPresent()) {
            Rol rol = rolOptional.get();
            rol.setExpenses(rolDTO.getExpenses());
            rol.setIncome(rolDTO.getIncome());
            rol.setSubSalary(rolDTO.getSubSalary());
            rolService.save(rol);
            return ResponseEntity.ok("rol actualizado");
        }
        return ResponseEntity.notFound().build();
    }
}
