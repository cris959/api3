package com.med.voll.api.domain.paciente;

import com.med.voll.api.domain.direccion.DatosDireccion;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Datos permitidos para modificar la información de un paciente")
public record DatosActualizacionPaciente (
        @NotNull
        @Schema(description = "Identificador único del paciente (obligatorio)",
                example = "25",
                requiredMode = Schema.RequiredMode.REQUIRED)
        Long id,
        @Schema(description = "Estado de actividad del paciente", example = "true")
        Boolean activo,
        @Schema(description = "Nombre completo actualizado", example = "Christian Doe")
        String nombre,
        @Schema(description = "Número de teléfono de contacto actualizado", example = "5491166778899")
        String telefono,
        @Schema(description = "Información de dirección actualizada")
        DatosDireccion direccion
){
}
