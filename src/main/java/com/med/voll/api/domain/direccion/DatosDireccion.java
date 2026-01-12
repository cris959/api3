package com.med.voll.api.domain.direccion;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Schema(description = "Información detallada de la ubicación geográfica")
public record DatosDireccion(
        @NotBlank
        @Schema(example = "Calle Falsa", description = "Nombre de la vía pública")
        String calle,
        @Schema(example = "123", description = "Número de la residencia o local")
        String numero,
        @Schema(example = "Apto 402", description = "Información adicional como torre, piso o referencia")
        String complemento,
        @Schema(example = "Centro", description = "Nombre del barrio o sector")
        @NotBlank String barrio,
        @NotBlank @Pattern(regexp = "\\d{4}")
        @Schema(example = "1425", description = "Código postal de 4 dígitos exactos")
        String codigo_postal,
        @NotBlank
        @Schema(example = "Villa Dolores", description = "Nombre de la ciudad")
        String ciudad,
        @NotBlank
        @Schema(example = "Córdoba", description = "Nombre del estado o provincia")
        String estado
) {
}
