package com.api.rober.DTO.Empleado;

import com.api.rober.Models.Empleado;
import com.api.rober.Models.Enum.EstadoCivil;

public record DatosListaEmpleados(
        Integer id,
        String nom,
        String ape,
        String correo,
        String telefono,
        String estadoCivil
) {
    public  DatosListaEmpleados (Empleado empleado) {
        this(
                empleado.getId(),
                empleado.getNom(),
                empleado.getApe(),
                empleado.getCorreo(),
                empleado.getTelefono(),
                empleado.getEstadoCivil().toString()
        );
    }
}
