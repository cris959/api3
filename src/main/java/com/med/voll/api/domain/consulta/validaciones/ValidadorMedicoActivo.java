package com.med.voll.api.domain.consulta.validaciones;

import com.med.voll.api.domain.ValidacionException;
import com.med.voll.api.domain.consulta.DatosReservaConsulta;
import com.med.voll.api.domain.medico.IMedicoRepository;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoActivo {

    private final IMedicoRepository medicoRepository;

    public ValidadorMedicoActivo(IMedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    public void validar(DatosReservaConsulta datos) {
        // Eleccion del MEdico opcional!!!
        if (datos.idMedico() == null) {
            return;
        }
        var medicoActivo = medicoRepository.findActivoById(datos.idMedico());
        if (!medicoActivo) {
            throw new ValidacionException("Consulta no puede ser reservada con Medico excluido!");
        }
    }
}
