package com.api.rober.DTO.Empleado;

import com.api.rober.Models.Empleado;
import com.api.rober.Models.Enum.EstadoCivil;
import lombok.Builder;

@Builder
public record DatosListaEmpleados(
        Integer id,
        String nom,
        String ape,
        String correo,
        String telefono,
        EstadoCivil estadoCivil
) {
    public  DatosListaEmpleados (Empleado empleado) {
        this(
                empleado.getId(),
                empleado.getNom(),
                empleado.getApe(),
                empleado.getCorreo(),
                empleado.getTelefono().toString(),
                empleado.getEstadoCivil()
        );
    }
}
