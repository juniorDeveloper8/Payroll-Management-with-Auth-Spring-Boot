package com.api.rober.Controllers;

import com.api.rober.Controllers.DTO.AreaDTO;
import com.api.rober.Entity.Area;
import com.api.rober.Services.IAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class AreaController {

    @Autowired
    private IAreaService areaService;

    @GetMapping("/allAreas")
    public ResponseEntity<?> findAll() {
        List<AreaDTO> areaDTOList = areaService.findAll()
                .stream()
                .map(area -> AreaDTO.builder()
                        .id_ar(area.getId_ar())
                        .name(area.getName())
                        .des(area.getDes())
                        .userList(area.getUserList())
                        .build())
                .toList();
        return ResponseEntity.ok(areaDTOList);
    }

    @GetMapping("/area/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        Optional<Area> areaOptional = areaService.findById(id);

        if (areaOptional.isPresent()) {
            Area area = areaOptional.get();

            AreaDTO areaDTO = AreaDTO.builder()
                    .id_ar(area.getId_ar())
                    .name(area.getName())
                    .des(area.getDes())
                    .userList(area.getUserList())
                    .build();
            return ResponseEntity.ok(areaDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/area")
    public ResponseEntity<?> save(@RequestBody AreaDTO areaDTO) throws URISyntaxException {
        if (areaDTO.getName().isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        Area area = Area.builder()
                .name(areaDTO.getName())
                .des(areaDTO.getDes())
                .userList(areaDTO.getUserList())
                .build();
        areaService.save(area);
        return ResponseEntity.created(new URI("/api/area")).build();
    }

    @PutMapping("/area/{id}")
    public ResponseEntity<?> areaUpdate(@PathVariable Integer id, @RequestBody AreaDTO areaDTO) {
        Optional<Area> areaOptional = areaService.findById(id);

        if (areaOptional.isPresent()) {
            Area area = areaOptional.get();
            area.setName(areaDTO.getName());
            area.setName(areaDTO.getDes());
            areaService.save(area);
            return ResponseEntity.ok("Area Actualizada ☆*: .｡. o(≧▽≦)o .｡.:*☆");
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/area/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer id) {

        if (id != null) {
            areaService.deleteById(id);
            return ResponseEntity.ok("Area eliminada (^///^)");
        }
        return ResponseEntity.badRequest().build();
    }
}
