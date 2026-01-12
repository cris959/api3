package com.med.voll.api.domain.medico;

import com.med.voll.api.domain.direccion.Direccion;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Respuesta detallada con toda la información de un médico")
public record DatosDetalleMedico(
        @Schema(example = "7", description = "Identificador único del médico")
        Long id,
        @Schema(example = "true", description = "Estado de activación en el sistema")
        Boolean activo,
        @Schema(example = "Christian Doe", description = "Nombre completo")
        String nombre,
        @Schema(example = "christian@voll.med", description = "Correo electrónico institucional")
        String email,
        @Schema(example = "123456", description = "Documento de identidad o colegiado")
        String documento,
        @Schema(example = "5491122334455", description = "Teléfono de contacto")
        String telefono,
        @Schema(description = "Especialidad médica principal")
        Especialidad especialidad,
        @Schema(description = "Datos completos de la ubicación física")
        Direccion direccion
) {
    public DatosDetalleMedico(Medico medico) {
        this(medico.getId(), medico.getActivo(), medico.getNombre(), medico.getEmail(), medico.getDocumento(),
                medico.getTelefono(), medico.getEspecialidad(), medico.getDireccion());
    }
}
