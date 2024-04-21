package com.api.rober.Controllers;

import com.api.rober.Controllers.DTO.UserDTO;
import com.api.rober.Entity.User;
import com.api.rober.Services.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "/api")
public class UserController {

    @Autowired
    private IUserService userService;

    @Operation(summary = "Get all users")
    @GetMapping(value = "/allUser")
    public ResponseEntity<?> findAll() {
        List<UserDTO> userDtoList = userService.findAll()
                .stream()
                .map(user -> UserDTO.builder()
                        .id_us(user.getId_us())
                        .name(user.getName())
                        .lastname(user.getLastname())
                        .email(user.getEmail())
                        .psw(user.getPsw())
                        .phone(user.getPhone())
                        .area(user.getArea())
                        .status(user.getStatus())
                        .build())
                .toList();
        return ResponseEntity.ok(userDtoList);
    }

    @Operation(summary = "Get user by id")
    @GetMapping(value = "/user/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        Optional<User> userOptional = userService.findById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            UserDTO userDTO = UserDTO.builder()
                    .id_us(user.getId_us())
                    .name(user.getName())
                    .lastname(user.getLastname())
                    .email(user.getEmail())
                    .psw(user.getPsw())
                    .phone(user.getPhone())
                    .status(user.getStatus())
                    .area(user.getArea())
                    .build();
            return ResponseEntity.ok(userDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Create user")
    @PostMapping(value = "/user")
    public ResponseEntity<?> save(@RequestBody UserDTO userDTO) throws URISyntaxException {

        if (userDTO.getName().isBlank() || userDTO.getLastname().isBlank() || userDTO.getArea() == null) {
            return ResponseEntity.badRequest().build();
        }

        User user = User.builder()
                .name(userDTO.getName())
                .lastname(userDTO.getLastname())
                .email(userDTO.getEmail())
                .psw(userDTO.getPsw())
                .phone(userDTO.getPhone())
                .status(userDTO.getStatus())
                .area(userDTO.getArea())
                .build();

        userService.save(user);
        return ResponseEntity.created(new URI("/api/user")).build();
    }

    @Operation(summary = "Create user employed")
    @PostMapping(value = "/user/employed")
    public ResponseEntity<?> saveEmployed(@RequestBody UserDTO userDTO) throws URISyntaxException {

        if (userDTO.getName().isBlank() || userDTO.getLastname().isBlank() || userDTO.getArea() == null) {
            return ResponseEntity.badRequest().build();
        }

        User user = User.builder()
                .name(userDTO.getName())
                .lastname(userDTO.getLastname())
                .email(userDTO.getEmail())
                .status(userDTO.getStatus())
                .area(userDTO.getArea())
                .build();

        userService.save(user);
        return ResponseEntity.created(new URI("/api/user/employed")).build();
    }

    @Operation(summary = "Update user")
    @PutMapping(value = "/user/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Integer id, @RequestBody UserDTO userDTO) {
        Optional<User> userOptional = userService.findById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setName(userDTO.getName());
            user.setLastname(userDTO.getLastname());
            user.setEmail(userDTO.getEmail());
            user.setPsw(userDTO.getPsw());
            user.setPhone(userDTO.getPhone());
            user.setArea(userDTO.getArea());
            userService.save(user);
            return ResponseEntity.ok("Usuario actualizado 👻");
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Delete user")
    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer id) {

        if (id != null) {
            userService.deleteById(id);
            return ResponseEntity.ok("Usuario Eliminado ☠️");
        }
        return ResponseEntity.badRequest().build();
    }
}


