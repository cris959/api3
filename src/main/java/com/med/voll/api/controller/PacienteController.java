package com.med.voll.api.controller;

import com.med.voll.api.domain.paciente.*;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private IPacienteRepository repository;

    @Transactional
    @PostMapping
    public ResponseEntity<DatosDetallePaciente> registrar(@Valid @RequestBody DatosRegistroPaciente datos,
                                                          UriComponentsBuilder uriComponentsBuilder) {
        var paciente = new Paciente(datos);
        repository.save(paciente);

        var uri = uriComponentsBuilder.path("/pacientes/{id}").buildAndExpand().toUri();
        return ResponseEntity.created(uri).body(new DatosDetallePaciente(paciente));
    }

    @GetMapping
    public ResponseEntity<Page<DatosListaPaciente>> listar(@PageableDefault(size = 10, sort = {"nombre"}) Pageable paginacion) {

//        try {
            var page = repository.findAllByActivoTrue(paginacion).map(DatosListaPaciente::new);
            return ResponseEntity.ok(page);
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().build();
//        }
    }

    @Transactional
    @PutMapping
    public ResponseEntity<DatosDetallePaciente> actualizar(@Valid @RequestBody DatosActualizacionPaciente datos){
        var paciente = repository.getReferenceById(datos.id());
        paciente.actualizarInformaciones(datos);
        return ResponseEntity.ok(new DatosDetallePaciente(paciente));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
//        if (!repository.existsById(id)) {
//            return ResponseEntity.notFound().build();
//        }
        var paciente = repository.getReferenceById(id);
        paciente.eliminar();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosDetallePaciente> detallar(@PathVariable Long id) {
//        if (!repository.existsById(id)) {
//            return ResponseEntity.notFound().build();
//        }
        var paciente = repository.getReferenceById(id);

        return ResponseEntity.ok(new DatosDetallePaciente(paciente));
    }
}
