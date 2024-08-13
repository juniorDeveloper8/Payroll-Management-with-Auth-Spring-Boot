package com.api.rober.Controllers;

import com.api.rober.DTO.Account.DatosJWTToken;
import com.api.rober.DTO.Account.DatosLoginAutenticacion;
import com.api.rober.DTO.Account.DatosRegistroAccount;
import com.api.rober.Interface.LoginInterface;
import com.api.rober.Models.Account.Login;
import com.api.rober.Services.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping( "/login")
@Tag(name = "Authentication", description = "Operaciones para la crecion de cuenta")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private LoginInterface loginInterface;



    @PostMapping
    @Operation(summary = "Autenticar un usuario", description = "Autentica un usuario y devuelve un JWT token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Token JWT generado exitosamente"),
            @ApiResponse(responseCode = "401", description = "Credenciales inválidas"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    public ResponseEntity<DatosJWTToken> autenticarUsuario(@RequestBody @Valid DatosLoginAutenticacion datosLoginAutenticacion) {
        try {
            Authentication authToken = new UsernamePasswordAuthenticationToken(
                    datosLoginAutenticacion.username(), datosLoginAutenticacion.password()
            );

            Authentication usuarioAutenticado = authenticationManager.authenticate(authToken);
            String jwtToken = tokenService.generarToken((Login) usuarioAutenticado.getPrincipal());
            return ResponseEntity.ok(new DatosJWTToken(jwtToken));

        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(404).body(new DatosJWTToken("Usuario no encontrado"));

        } catch (BadCredentialsException e) {

            return ResponseEntity.status(401).body(new DatosJWTToken("Credenciales inválidas"));
        }
    }

    @PostMapping("/create")
    @Operation(summary = "Registrar un nuevo usuario", description = "Crea un nuevo usuario en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
            @ApiResponse(responseCode = "409", description = "El nombre de usuario ya está en uso")
    })
    public ResponseEntity<?> registrarUsuario(@RequestBody @Valid DatosRegistroAccount datosRegistroAccount) {
        if (datosRegistroAccount.username().isBlank() || datosRegistroAccount.password().isBlank()) {
            return ResponseEntity.badRequest().body("El nombre de usuario y la contraseña no pueden estar en blanco");
        }
        if (loginInterface.existsByUsername(datosRegistroAccount.username())) {
            return ResponseEntity.status(409).body("El nombre de usuario ya está en uso");
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String claveEncriptada = passwordEncoder.encode(datosRegistroAccount.password());

        Login login = Login.builder()
                .username(datosRegistroAccount.username())
                .password(claveEncriptada)
                .activo(true)
                .build();

        Login loginSave = loginInterface.save(login);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(loginSave.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

}
