package com.med.voll.api.domain.consulta;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Representa los detalles de una consulta médica ya registrada")
public record DatosDetalleConsulta(
        @Schema(example = "101", description = "Identificador único de la consulta")
        Long id,
        @Schema(example = "5", description = "ID del médico asignado")
        Long idMedico,
        @Schema(example = "22", description = "ID del paciente")
        Long idPaciente,
        @Schema(example = "2026-01-20T14:00:00", description = "Fecha y hora programada")
        LocalDateTime fecha
) {
    public DatosDetalleConsulta(Consulta consulta) {
        this(consulta.getId(), consulta.getMedico().getId(), consulta.getPaciente().getId(), consulta.getFecha());
    }
}
