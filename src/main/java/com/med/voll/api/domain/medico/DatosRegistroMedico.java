package com.med.voll.api.domain.medico;

import com.med.voll.api.domain.direccion.DatosDireccion;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Schema(description = "Datos necesarios para dar de alta a un nuevo médico en el sistema")
public record DatosRegistroMedico(
        @NotBlank
        @Schema(example = "Christian Doe", description = "Nombre completo del médico")
        String nombre,
        @NotBlank
        @Email
        @Schema(example = "christian.garay@voll.med", description = "Correo electrónico institucional único")
        String email,
        @NotBlank(message = "Teléfono es obligatorio")
        @Pattern(regexp = "\\d{10,15}", message = "Solo números (10-15 dígitos)")
        @Schema(example = "5491122334455", description = "Teléfono con código de área (10 a 15 dígitos)")
        String telefono,
        @NotBlank
        @Pattern(regexp = "\\d{8}", message = "El DNI debe tener exactamente 8 dígitos numéricos")
        @Schema(example = "40123456", description = "Documento Nacional de Identidad (8 dígitos exactos)")
        String documento,
        @NotNull
        @Schema(description = "Especialidad médica (CARDIOLOGIA, GINECOLOGIA, ORTOPEDIA, PEDIATRIA)")
        Especialidad especialidad,
        @NotNull
        @Valid
        @Schema(description = "Datos de ubicación física del consultorio")
        DatosDireccion direccion
) {}
