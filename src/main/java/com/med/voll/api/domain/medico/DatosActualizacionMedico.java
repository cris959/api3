package com.med.voll.api.domain.medico;

import com.med.voll.api.domain.direccion.DatosDireccion;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Datos permitidos para la actualización de un médico")
public record DatosActualizacionMedico (
        @NotNull
        @Schema(description = "ID del médico que se desea actualizar", example = "7", requiredMode = Schema.RequiredMode.REQUIRED)
        Long id,
        @Schema(description = "Estado de actividad del médico en el sistema", example = "true")
        Boolean activo,
        @Schema(description = "Nombre completo del médico", example = "Christian Doe")
        String nombre,
        @Schema(description = "Teléfono de contacto", example = "5491122334455")
        String telefono,
        @Schema(description = "Información de la dirección (se pueden actualizar campos específicos)")
        DatosDireccion direccion
){
}
