package com.med.voll.api.controller;

import com.med.voll.api.domain.direccion.DatosDireccion;
import com.med.voll.api.domain.direccion.Direccion;
import com.med.voll.api.domain.medico.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test") // <--- Esto hace que busque application-test.properties
@AutoConfigureJsonTesters
class MedicoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<DatosRegistroMedico> datosRegistroMedicoJson;

    @Autowired
    private JacksonTester<DatosDetalleMedico> datosDetalleMedicoJson;

    @MockitoBean // O @MockBean según tu versión
    private IMedicoRepository repository;

    @Test
    @DisplayName("Deberia devolver http 400 cuando request esta vacia")
    @WithMockUser
    void registrar_escenario1() throws Exception {

        var response = mockMvc.perform(post("/medicos"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());

    }

    @Test
    @DisplayName("Deberia devolver http 201 cuando request reciba un json valido!")
    @WithMockUser
    void registrar_escenario2() throws Exception {

// 1. GIVEN: Preparamos los datos
        var direccion = new DatosDireccion("Calle x", "123", "z", "El Sol", "1234", "Villa Dolores", "Cordoba");
        var datosRegistro = new DatosRegistroMedico("Medico Test", "test@med.com", "1234598766", "11200233", Especialidad.CARDIOLOGIA, direccion);

// Datos que esperamos que el Service/Repository devuelva (Response)
        var datosDetalle = new DatosDetalleMedico(
                null, // El ID suele ser null antes de persistir o generado por el mock
                true,
                datosRegistro.nombre(),
                datosRegistro.email(),
                datosRegistro.documento(),
                datosRegistro.telefono(),
                datosRegistro.especialidad(),
                new Direccion(direccion));

//        var medicoSimulado = new Medico(datosRegistro);
        var response = mockMvc.perform(post("/medicos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(datosRegistroMedicoJson.write(datosRegistro).getJson()
                        )
                )
                .andReturn().getResponse();

        // Un POST exitoso suele ser 201 Created
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        var jsonEsperado = datosDetalleMedicoJson.write(datosDetalle).getJson();
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }
}