package com.med.voll.api.domain.usuario;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Credenciales necesarias para el inicio de sesión")
public record DatosAutenticacion(
        @Schema(example = "admin@voll.med", description = "Nombre de usuario o correo electrónico")
        String login,
        @Schema(example = "123456", description = "Contraseña del usuario", format = "password")
        String contrasena
) {
}
