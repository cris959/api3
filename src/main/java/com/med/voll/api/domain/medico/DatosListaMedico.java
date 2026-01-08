package com.med.voll.api.domain.medico;

public record DatosListaMedico(
        Long id,
        Boolean activo,
        String nombre,
        String email,
        String documento,
        Especialidad especialidad
) {
    public DatosListaMedico(Medico medico) {
        this(medico.getId(), medico.getActivo(), medico.getNombre(), medico.getEmail(), medico.getDocumento(), medico.getEspecialidad());
    }
}
