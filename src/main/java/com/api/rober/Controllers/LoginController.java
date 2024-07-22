package com.api.rober.Controllers;

import com.api.rober.Controllers.DTO.User.DTOJWTToken;
import com.api.rober.Controllers.DTO.User.DTOUsuarioAuthentication;
import com.api.rober.Controllers.DTO.User.DatosRegistroUserLogin;
import com.api.rober.Entity.Usuario;
import com.api.rober.Repositories.UsuarioRepository;
import com.api.rober.Services.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/login")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    @Operation(
            summary = "login user",
            description = "Authenticate a user and return the authentication token alog with user details.",
            tags = {"Authentication"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Authentication request with username and password",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = DTOUsuarioAuthentication.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful authentication",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = DTOJWTToken.class)
                            )
                    )
            }
    )
    public ResponseEntity<?> autenticarUsuario(@RequestBody @Valid DTOUsuarioAuthentication dtoUsuarioAuthentication) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(dtoUsuarioAuthentication.login(),
        dtoUsuarioAuthentication.clave());
        var usuarioAutenticado = authenticationManager.authenticate(authToken);
        var JWTToken = tokenService.generateToken((Usuario) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new DTOJWTToken(JWTToken));
    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> registrarUsuario(@RequestBody @Valid DatosRegistroUserLogin datosRegistroUserLogin) {
        if (datosRegistroUserLogin.login().isBlank() || datosRegistroUserLogin.clave().isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        // Encriptar la contraseña utilizando BCryptPasswordEncoder
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String claveEncriptada = passwordEncoder.encode(datosRegistroUserLogin.clave());

        // Construir la entidad Usuarios con la contraseña encriptada
        Usuario usuario = Usuario.builder()
                .login(datosRegistroUserLogin.login())
                .clave(claveEncriptada) // Guardar la contraseña encriptada
                .build();

        // Guardar el usuario en la base de datos
        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        // Retornar una respuesta con código 201 Created y la URI del recurso creado
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(usuarioGuardado.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
