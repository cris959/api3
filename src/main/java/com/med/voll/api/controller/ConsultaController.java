package com.med.voll.api.controller;

import com.med.voll.api.domain.consulta.DatosCancelamientoConsulta;
import com.med.voll.api.domain.consulta.DatosReservaConsulta;
import com.med.voll.api.domain.consulta.ReservaDeConsulta;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultas")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Consultas", description = "Operaciones para reservar y cancelar citas médicas")
public class ConsultaController {


    private final ReservaDeConsulta consulta;

    public ConsultaController(ReservaDeConsulta consulta) {
        this.consulta = consulta;
    }

    @Transactional
    @PostMapping
    @Operation(
            summary = "Reserva una nueva consulta",
            description = "Este endpoint permite agendar una cita validando la disponibilidad del médico y del paciente."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta reservada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos o reglas de negocio violadas"),
            @ApiResponse(responseCode = "403", description = "No autorizado - Token JWT inexistente o inválido")
    })
    public ResponseEntity reservar(@Valid @RequestBody DatosReservaConsulta datos) {

        var detalleConsulta = consulta.reservar(datos);
        return ResponseEntity.ok(detalleConsulta);
    }

    @DeleteMapping
    @Transactional
    @Operation(
            summary = "Cancela una consulta existente",
            description = "Permite cancelar una cita siempre que sea con al menos 24 horas de antelación."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Consulta cancelada correctamente"),
            @ApiResponse(responseCode = "404", description = "El ID de la consulta no existe")
    })
    public ResponseEntity cancelar(@RequestBody @Valid DatosCancelamientoConsulta datos) {
        consulta.cancelar(datos);
        return ResponseEntity.noContent().build();
    }
}
