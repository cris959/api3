package com.med.voll.api.domain.medico;

import com.med.voll.api.domain.direccion.DatosDireccion;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DatosRegistroMedico(
        @NotBlank
        String nombre,
        @NotBlank @Email
        String email,
        @NotBlank(message = "Teléfono es obligatorio")
        @Pattern(regexp = "\\d{10,15}", message = "Solo números (10-15 dígitos)")
        String telefono,
        @NotBlank @Pattern(regexp = "\\d{8}", message = "El DNI debe tener exactamente 8 dígitos numéricos")
        String documento,
        @NotNull
        Especialidad especialidad,
        @NotNull @Valid
        DatosDireccion direccion
) {}
