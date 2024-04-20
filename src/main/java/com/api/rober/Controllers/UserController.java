package com.api.rober.Controllers;

import com.api.rober.Controllers.DTO.UserDTO;
import com.api.rober.Services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")

public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping
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
                        .build())
                .toList();
    return ResponseEntity.ok(userDtoList);
    }

}
