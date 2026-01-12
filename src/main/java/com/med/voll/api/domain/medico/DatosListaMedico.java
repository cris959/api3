package com.med.voll.api.domain.medico;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Datos resumidos del médico para visualización en listas")
public record DatosListaMedico(
        @Schema(example = "7")
        Long id,
        @Schema(example = "true", description = "Indica si el médico está disponible en el sistema")
        Boolean activo,
        @Schema(example = "Christian Doe")
        String nombre,
        @Schema(example = "christian@voll.med")
        String email,
        @Schema(example = "123456", description = "Número de documento de identidad o registro profesional")
        String documento,
        @Schema(example = "CARDIOLOGIA", description = "Especialidad médica en la que se desempeña")
        Especialidad especialidad
) {
    public DatosListaMedico(Medico medico) {
        this(medico.getId(), medico.getActivo(), medico.getNombre(), medico.getEmail(), medico.getDocumento(), medico.getEspecialidad());
    }
}
