package com.med.voll.api.domain.paciente;

import com.med.voll.api.domain.direccion.DatosDireccion;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Schema(description = "Datos necesarios para registrar un nuevo paciente en el sistema")
public record DatosRegistroPaciente(
        @NotBlank
        @Schema(example = "Christian Doe", description = "Nombre completo del paciente")
        String nombre,
        @NotBlank @Email
        @Schema(example = "paciente@ejemplo.com", description = "Correo electrónico de contacto")
        String email,
        @NotBlank @Pattern(regexp = "\\d{8}", message = "El DNI debe tener exactamente 8 dígitos numéricos")
        @Schema(example = "40123456", description = "Documento de identidad (8 dígitos numéricos exactos)")
        String documento,
        @NotBlank(message = "Teléfono es obligatorio")
        @Pattern(regexp = "\\d{10,15}", message = "Solo números (10-15 dígitos)")
        @Schema(example = "5491166778899", description = "Teléfono de contacto (entre 10 y 15 dígitos)")
        String telefono,
        @NotNull @Valid
        @Schema(description = "Dirección de residencia del paciente")
        DatosDireccion direccion
        ) {
}
