package com.med.voll.api.domain.consulta.validaciones;

import com.med.voll.api.domain.ValidacionException;
import com.med.voll.api.domain.consulta.DatosReservaConsulta;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidadorFueraHorarioConsultas implements IValidadorDeConsultas {

    public void validar(DatosReservaConsulta datos) {
        var fechaConsulta = datos.fecha();
        var domingo = fechaConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var horarioAntesDeApertura = fechaConsulta.getHour() < 7;
        var horaioDespuesDeCierre = fechaConsulta.getHour() > 18;
        if (domingo || horarioAntesDeApertura || horaioDespuesDeCierre) {
            throw new ValidacionException("Horario selecionado fuera del horario de atencion de la Clinica!");
        }
    }
}
