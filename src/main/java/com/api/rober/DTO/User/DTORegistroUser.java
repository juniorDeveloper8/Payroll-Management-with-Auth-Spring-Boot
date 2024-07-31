package com.api.rober.DTO.User;

import com.api.rober.Entity.Area;
import com.api.rober.Entity.Document;
import jakarta.validation.constraints.NotBlank;

public record DTORegistroUser(
        String phone,
        @NotBlank
        String name,
        @NotBlank
        String lastname,
        @NotBlank
        String email,
        Document document
) {
}
