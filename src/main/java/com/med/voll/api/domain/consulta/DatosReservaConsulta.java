package com.med.voll.api.domain.consulta;

import com.med.voll.api.domain.medico.Especialidad;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosReservaConsulta(
        @Schema(example = "1", description = "ID del médico que atenderá la consulta. Opcional si se elige especialidad.")
        Long idMedico,
        @NotNull
        @Schema(example = "42", description = "ID del paciente que reserva la consulta")
        Long idPaciente,

        @NotNull
        @Future
        @Schema(example = "2026-05-20T10:30:00", description = "Fecha y hora de la cita (ISO 8601)")
        LocalDateTime fecha,

        @Schema(description = "Especialidad del médico. Solo necesaria si no se provee un idMedico específico.")
        Especialidad especialidad
) {
}
