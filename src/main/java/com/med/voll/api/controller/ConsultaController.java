package com.med.voll.api.controller;

import com.med.voll.api.domain.consulta.DatosDetalleConsulta;
import com.med.voll.api.domain.consulta.DatosReservaConsulta;
import com.med.voll.api.domain.consulta.ReservaDeConsulta;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ReservaDeConsulta consulta;

    @Transactional
    @PostMapping
    public ResponseEntity reservar(@Valid @RequestBody DatosReservaConsulta datos) {


        consulta.reservar(datos);
        return ResponseEntity.ok(new DatosDetalleConsulta(null, null, null, null));
    }
}
