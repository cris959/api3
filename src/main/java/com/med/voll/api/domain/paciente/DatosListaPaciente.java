package com.med.voll.api.domain.paciente;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Datos resumidos de pacientes para visualización en listados")
public record DatosListaPaciente (
        @Schema(example = "25")
        Long id,
        @Schema(example = "true", description = "Estado de actividad del paciente")
        Boolean activo,
        @Schema(example = "Christian Doe")
        String nombre,
        @Schema(example = "paciente@ejemplo.com")
        String email,
        @Schema(example = "40123456", description = "Documento de identidad")
        String documento
){
    public DatosListaPaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getActivo(), paciente.getNombre(), paciente.getEmail(), paciente.getDocumento());
    }
}
