package com.api.rober.DTO.User;

import jakarta.validation.constraints.NotNull;

public record DTOUpdateUser(

        @NotNull
        Integer id,
        String phone,
        String name,
        String lastname,
        String email
) {
}
