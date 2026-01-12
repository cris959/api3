package com.med.voll.api.infra.security;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Contenedor del token de acceso generado tras una autenticación exitosa")
public record DatosTokenJWT(
        @Schema(
                description = "Token JWT (JSON Web Token) que debe enviarse en el encabezado 'Authorization: Bearer <token>'",
                example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTcwNTE5MzYwMH0..."
        )
        String token
) {
}
