package com.med.voll.api.domain.paciente;

import com.med.voll.api.domain.direccion.Direccion;

public record DatosDetallePaciente(
        Long id,
        Boolean activo,
        String nombre,
        String email,
        String documento,
        String telefono,
        Direccion direccion
) {
    public DatosDetallePaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getActivo(), paciente.getNombre(), paciente.getEmail(), paciente.getDocumento(),
                paciente.getTelefono(), paciente.getDireccion());

    }
}
