package com.med.voll.api.domain.consulta.validaciones;

import com.med.voll.api.domain.ValidacionException;
import com.med.voll.api.domain.consulta.DatosReservaConsulta;
import com.med.voll.api.domain.paciente.IPacienteRepository;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteActivo implements IValidadorDeConsultas {

    private final IPacienteRepository pacienteRepository;

    public ValidadorPacienteActivo(IPacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public void validar(DatosReservaConsulta datos) {
        var pacienteEstaActivo = pacienteRepository.findActivoById(datos.idPaciente());
        if (!pacienteEstaActivo) {
            throw new ValidacionException("Consulta no puede ser reservada con Paciente excluido!");
        }
    }
}
