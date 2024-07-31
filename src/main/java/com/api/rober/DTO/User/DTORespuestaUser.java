package com.api.rober.DTO.User;

public record DTORespuestaUser(
        Integer id,
        String phone,
        String name,
        String lastname,
        String email,
        String document
) {
}
