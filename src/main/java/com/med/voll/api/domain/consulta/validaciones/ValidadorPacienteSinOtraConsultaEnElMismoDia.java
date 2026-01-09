package com.med.voll.api.domain.consulta.validaciones;

import com.med.voll.api.domain.ValidacionException;
import com.med.voll.api.domain.consulta.DatosReservaConsulta;
import com.med.voll.api.domain.consulta.IConsultaRepository;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteSinOtraConsultaEnElMismoDia {

    private final IConsultaRepository consultaRepository;

    public ValidadorPacienteSinOtraConsultaEnElMismoDia(IConsultaRepository consultaRepository) {
        this.consultaRepository = consultaRepository;
    }

    public void validar(DatosReservaConsulta datos) {
        var primerHorario = datos.fecha().withHour(7);
        var ultimoHorario = datos.fecha().withHour(18);
        var pacienteTieneOtraConsultaEnElDia = consultaRepository.existsByPacienteIdAndFechaBetween(datos.idPaciente(), primerHorario, ultimoHorario);
           if (pacienteTieneOtraConsultaEnElDia) {
            throw new ValidacionException("Paciente ya tiene una consulta reservada para ese dia!");
        }
    }
}
