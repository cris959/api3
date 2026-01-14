package com.med.voll.api.domain.consulta;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Datos necesarios para cancelar una consulta existente")
public record DatosCancelamientoConsulta(
        @NotNull
        @Schema(example = "101", description = "ID de la consulta que se desea cancelar")
        Long idConsulta,
        @NotNull
        @Schema(description = "Motivo del cancelamiento. Valores permitidos: PACIENTE_DESISTIO, MEDICO_CANCELO, OTROS.",
                implementation = MotivoCancelamiento.class)
        MotivoCancelamiento motivo
) {
        public boolean isEmpty() {
                return idConsulta == null || motivo == null;
        }
}
