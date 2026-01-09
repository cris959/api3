package com.med.voll.api.domain.consulta.validaciones;

import com.med.voll.api.domain.ValidacionException;
import com.med.voll.api.domain.consulta.DatosReservaConsulta;
import com.med.voll.api.domain.consulta.IConsultaRepository;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoConOtraConsultaEnElMismoHorario implements IValidadorDeConsultas {

    private final IConsultaRepository consultaRepository;

    public ValidadorMedicoConOtraConsultaEnElMismoHorario(IConsultaRepository consultaRepository) {
        this.consultaRepository = consultaRepository;
    }

    public void validar(DatosReservaConsulta datos) {
        var medicoTieneOtraConsultaEnELMismoHorario = consultaRepository.existsByMedicoIdAndFecha(datos.idMedico(), datos.fecha());
        if (medicoTieneOtraConsultaEnELMismoHorario) {
            throw new ValidacionException("Medico ya tiene otra consulta en esa misma fecha y hora");
        }
    }
}
