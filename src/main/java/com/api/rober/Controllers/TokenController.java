package com.api.rober.Controllers;

import com.api.rober.Services.Component.StaticTokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/tokenTest")
@Tag(name = "Autentication Testig", description = "Operaciones de creacion de cuenta con token estatico")
public class TokenController {

    private final StaticTokenProvider tokenProvider;

    public TokenController(StaticTokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @GetMapping
    @Operation(
            summary = "Obtener token JWT estático",
            description = "Este endpoint proporciona un token JWT estático generado por el StaticTokenProvider."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Token JWT generado exitosamente")
    })
    public Map<String, String> getToken() {
        return Map.of("jwtToken", tokenProvider.getStaticToken());
    }
}
