package com.med.voll.api.domain.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;


public interface IMedicoRepository extends JpaRepository<Medico, Long> {


    Page<Medico> findAllByActivoTrue(Pageable paginacion);


    @Query("""
            SELECT m FROM Medico m
            WHERE
            m.activo = true
            AND
            m.especialidad = :especialidad
            AND m.id NOT IN (
            SELECT c.medico.id FROM Consulta c
            WHERE
            c.fecha = :fecha
            )
            ORDER BY RAND()
            """)
    Medico elegirMedicoAleratorioEnLaFecha(@Param("especialidad") Especialidad especialidad, @Param("fecha") LocalDateTime fecha);

    @Query("""
            SELECT m.activo
            FROM Medico m
            WHERE
            m.id = :idMedico
            """)
    boolean findActivoById(Long idMedico);
}
