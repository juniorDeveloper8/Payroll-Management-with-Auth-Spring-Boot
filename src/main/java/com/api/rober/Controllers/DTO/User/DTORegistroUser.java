package com.api.rober.Controllers.DTO.User;

import com.api.rober.Entity.Area;
import jakarta.validation.constraints.NotBlank;

public record DTORegistroUser(
        String psw,
        String phone,
        @NotBlank
        String name,
        @NotBlank
        String lastname,
        @NotBlank
        String email,
        @NotBlank
        Area area
) {
}
