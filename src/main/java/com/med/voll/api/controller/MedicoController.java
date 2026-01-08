package com.med.voll.api.controller;

import com.med.voll.api.domain.medico.*;

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
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private IMedicoRepository repository;

    @Transactional
    @PostMapping
    public ResponseEntity<DatosDetalleMedico> registrar(@Valid @RequestBody DatosRegistroMedico datos,
                                                        UriComponentsBuilder uriComponentsBuilder)  {
        var medico = new Medico(datos);
        repository.save(medico);
        var uri = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DatosDetalleMedico(medico));
    }

    @GetMapping
    public ResponseEntity<Page<DatosListaMedico>> listar(@PageableDefault(size = 10, sort = {"nombre"}) Pageable paginacion) {

            var page = repository.findAllByActivoTrue(paginacion).map(DatosListaMedico::new);
            return ResponseEntity.ok(page);

    }

    @Transactional
    @PutMapping
    public ResponseEntity<DatosDetalleMedico> actualizar(@Valid @RequestBody DatosActualizacionMedico datos){
        var medico = repository.getReferenceById(datos.id());
        medico.actualizarInformaciones(datos);
        return ResponseEntity.ok(new DatosDetalleMedico(medico));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
//        if (!repository.existsById(id)) {
//            return ResponseEntity.notFound().build(); // 404 si no existe!!!
//        }
        var medico = repository.getReferenceById(id);
        medico.eliminar();
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{id}")
    public ResponseEntity<DatosDetalleMedico> detallar(@PathVariable Long id) {
//        if (!repository.existsById(id)) {
//            return ResponseEntity.notFound().build(); // 404 si no existe!!!
//        }
        var medico = repository.getReferenceById(id);

        return ResponseEntity.ok(new DatosDetalleMedico(medico));
    }
}
