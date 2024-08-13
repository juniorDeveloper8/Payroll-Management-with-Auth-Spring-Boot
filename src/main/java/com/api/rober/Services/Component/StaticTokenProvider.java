package com.api.rober.Services.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Component
public class StaticTokenProvider {

    @Value("${api.security.secret}")
    private String apiSecret;

    private String staticToken;

    @PostConstruct
    public void init() {
        if (apiSecret == null || apiSecret.isEmpty()) {
            throw new IllegalArgumentException("The Secret cannot be null or empty");
        }
        Algorithm algorithm = Algorithm.HMAC256(apiSecret);
        Instant expirationTime = LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.UTC);

        staticToken = JWT.create()
                .withIssuer("voll med")
                .withSubject("static_user")
                .withClaim("id", 1)
                .withExpiresAt(expirationTime)
                .sign(algorithm);
    }

    public String getStaticToken() {
        return staticToken;
    }
}