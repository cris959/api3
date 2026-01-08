package com.med.voll.api.domain.paciente;


public record DatosListaPaciente (
        Long id,
        Boolean activo,
        String nombre,
        String email,
        String documento
){
    public DatosListaPaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getActivo(), paciente.getNombre(), paciente.getEmail(), paciente.getDocumento());
    }
}
