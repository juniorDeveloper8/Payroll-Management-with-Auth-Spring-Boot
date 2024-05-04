package com.api.rober.Controllers;

import com.api.rober.Controllers.DTO.User.DTOJWTToken;
import com.api.rober.Controllers.DTO.User.DTOUsuarioAuthentication;
import com.api.rober.Entity.Usuario;
import com.api.rober.Services.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/login")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<?> autenticarUsuario(@RequestBody @Valid DTOUsuarioAuthentication dtoUsuarioAuthentication) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(dtoUsuarioAuthentication.login(),
        dtoUsuarioAuthentication.clave());
        var usuarioAutenticado = authenticationManager.authenticate(authToken);
        var JWTToken = tokenService.generateToken((Usuario) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new DTOJWTToken(JWTToken));
    }
}
