package com.med.voll.api.domain.consulta;


import com.med.voll.api.domain.ValidacionException;
import com.med.voll.api.domain.consulta.validaciones.IValidadorDeConsultas;
import com.med.voll.api.domain.medico.IMedicoRepository;
import com.med.voll.api.domain.medico.Medico;
import com.med.voll.api.domain.paciente.IPacienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaDeConsulta {

    private final IMedicoRepository medicoRepository;

    private final IPacienteRepository pacienteRepository;

    private final IConsultaRepository consultaRepository;

    private final List<IValidadorDeConsultas> validadores;

    public ReservaDeConsulta(IMedicoRepository medicoRepository, IPacienteRepository pacienteRepository, IConsultaRepository consultaRepository, List<IValidadorDeConsultas> validadores) {
        this.medicoRepository = medicoRepository;
        this.pacienteRepository = pacienteRepository;
        this.consultaRepository = consultaRepository;
        this.validadores = validadores;
    }

    public DatosDetalleConsulta reservar(DatosReservaConsulta datos) {

        if (!pacienteRepository.existsById(datos.idPaciente())) {
            throw new ValidacionException("No existe un paciente con el id informado");
        }
        if (datos.idMedico() != null && !medicoRepository.existsById(datos.idMedico())) {
            throw new ValidacionException("No existe un medico con el id informado");
        }

        // Validaciones
        validadores.forEach(v ->v.validar(datos));

        var medico = elegirMedico(datos);
        if (medico != null) {
            throw new ValidacionException("No existe un medico disponible en ese horario");
        }
        var paciente = pacienteRepository.findById(datos.idPaciente()).get();
        var consulta = new Consulta(null, medico, paciente, datos.fecha(), null);
        consultaRepository.save(consulta);
        return new DatosDetalleConsulta(consulta);
    }

    private Medico elegirMedico(DatosReservaConsulta datos) {
        if (datos.idMedico() != null) {
            return medicoRepository.getReferenceById(datos.idMedico());
        }
        if (datos.especialidad() == null) {
            throw new ValidacionException("Es necesario elegir la especialidad cuando no se elije un medico.");
        }
        return medicoRepository.elegirMedicoAleratorioEnLaFecha(datos.especialidad(), datos.fecha());
    }

    public void cancelar(DatosCancelamientoConsulta datos) {
        if (!consultaRepository.existsById(datos.idConsulta())) {
            throw new ValidacionException("Id de la consulta informado no existe!");
        }
        var consulta = consultaRepository.getReferenceById(datos.idConsulta());
        consulta.cancelar(datos.motivo());
    }
}
