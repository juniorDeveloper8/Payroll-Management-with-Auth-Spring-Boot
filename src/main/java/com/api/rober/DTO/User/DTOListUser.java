package com.api.rober.DTO.User;

import com.api.rober.Entity.User;
import lombok.Builder;
import lombok.Data;

@Builder
public record DTOListUser(
        Integer id_us,
        String phone,
        String name,
        String lastname,
        String email,
        String document

) {

    public DTOListUser(User user) {
        this(
                user.getId_us(),
                user.getName(),
                user.getLastname(),
                user.getEmail(),
                user.getPhone(),
                user.getDocumentList().toString()
        );
    }
}
