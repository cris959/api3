package com.med.voll.api.domain.medico;

import com.med.voll.api.domain.direccion.DatosDireccion;
import jakarta.validation.constraints.NotNull;

public record DatosActualizacionMedico (
        @NotNull Long id,
        Boolean activo,
        String nombre,
        String telefono,
        DatosDireccion direccion
){
}
