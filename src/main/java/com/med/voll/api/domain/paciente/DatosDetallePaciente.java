package com.med.voll.api.domain.paciente;

import com.med.voll.api.domain.direccion.Direccion;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Información detallada de un paciente registrado en el sistema")
public record DatosDetallePaciente(
        @Schema(example = "25", description = "Identificador único del paciente")
        Long id,
        @Schema(example = "true", description = "Indica si el paciente tiene el registro activo")
        Boolean activo,
        @Schema(example = "Christian Doe", description = "Nombre completo del paciente")
        String nombre,
        @Schema(example = "paciente@ejemplo.com", description = "Correo electrónico de contacto")
        String email,
        @Schema(example = "40123456", description = "Número de documento de identidad")
        String documento,
        @Schema(example = "5491166778899", description = "Número de teléfono de contacto")
        String telefono,
        @Schema(description = "Información completa de la dirección del paciente")
        Direccion direccion
) {
    public DatosDetallePaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getActivo(), paciente.getNombre(), paciente.getEmail(), paciente.getDocumento(),
                paciente.getTelefono(), paciente.getDireccion());

    }
}
