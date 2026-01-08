package com.med.voll.api.domain.medico;

import com.med.voll.api.domain.direccion.Direccion;

public record DatosDetalleMedico(
        Long id,
        Boolean activo,
        String nombre,
        String email,
        String documento,
        String telefono,
        Especialidad especialidad,
        Direccion direccion
) {
    public DatosDetalleMedico(Medico medico) {
        this(medico.getId(), medico.getActivo(), medico.getNombre(), medico.getEmail(), medico.getDocumento(),
                medico.getTelefono(), medico.getEspecialidad(), medico.getDireccion());
    }
}
