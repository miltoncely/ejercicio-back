package com.example.pragma.servicioclientes.infrastructure.entrypoints;

import com.example.pragma.servicioclientes.application.mappers.MapeadorDtoModelo;
import com.example.pragma.servicioclientes.domain.usecase.GestionarClientes;
import com.example.pragma.servicioclientes.infrastructure.entrypoints.dtos.ClientePeticion;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.example.pragma.servicioclientes.infrastructure.helpers.DatosTest.*;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(RecursoClientes.class)
class RecursoClientesTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GestionarClientes gestionarClientes;

    @MockBean
    private MapeadorDtoModelo mapeadorDto;

    @MockBean
    private MessageSource messageSource;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("Consultar el cliente por Identificacion")
    void testConsultarClientePorIdentificacion() throws Exception {
        //given
        when(gestionarClientes.consultarCliente("CC", "1000000000")).thenReturn(getCliente());
        when(mapeadorDto.aRespuesta(getCliente())).thenReturn(getClienteRespuesta());

        //when
        mockMvc.perform(get("/clientes/CC/1000000000").contentType(MediaType.APPLICATION_JSON))
                //then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.tipoDeDocumento").value("CC"))
                .andExpect(jsonPath("$.numeroDeIdentificacion").value("1000000000"))
                .andExpect(jsonPath("$.nombres").value("Milton Cely"))
                .andExpect(jsonPath("$.apellidos").value("Cely Carrascal"))
                .andExpect(jsonPath("$.ciudadDeNacimiento").value("Cucuta"));
        verify(gestionarClientes).consultarCliente("CC", "1000000000");
    }

    @Test
    @DisplayName("Guardar un cliente nuevo")
    void testGuardarUnCliente() throws Exception {
        //given
        when(gestionarClientes.consultarCliente("CC", "1000000001")).thenReturn(null);
        when(mapeadorDto.aModelo(getClientePeticionGuardar())).thenReturn(getClienteGuardar());
        when(gestionarClientes.guardarCliente(getClienteGuardar())).thenReturn(getClienteGuardar());
        when(mapeadorDto.aRespuesta(getClienteGuardar())).thenReturn(getClienteRespuestaGuardar());

        //when
        mockMvc.perform(post("/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(getClientePeticionGuardar())))

                //then
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.tipoDeDocumento").value("CC"))
                .andExpect(jsonPath("$.numeroDeIdentificacion").value("1000000001"))
                .andExpect(content().json(objectMapper.writeValueAsString(getClienteRespuestaGuardar())));
    }

    @Test
    @DisplayName("Listar los clientes mayores o igual a edad determinada")
    void testListarClientesPorEdad() throws Exception {
        //given
        when(gestionarClientes.listarClientesPorEdad(22)).thenReturn(getClientesConsultadosPorEdad());
        when(mapeadorDto.aRespuestas(getClientesConsultadosPorEdad())).thenReturn(getClientesRespuestaConsultadosPorEdad());
        //when
        mockMvc.perform(get("/clientes/edad/22")
                .contentType(MediaType.APPLICATION_JSON))

                //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*",hasSize(2)))
                .andDo(print());
    }

    @Test
    @DisplayName("Listar todos los clientes")
    void testListarClientes() throws Exception {
        //given
        when(gestionarClientes.listarClientes()).thenReturn(getListadoClientes());
        when(mapeadorDto.aRespuestas(getListadoClientes())).thenReturn(getListadoClientesRespuesta());
        //when
        mockMvc.perform(get("/clientes")
                .contentType(MediaType.APPLICATION_JSON))

                //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*",hasSize(3)))
                .andDo(print());
    }

    @Test
    void testActualizarCliente() throws Exception {
        //given
        when(gestionarClientes.consultarCliente("CC", "1000000001")).thenReturn(getClienteGuardar());
        when(mapeadorDto.aModelo(getClientePeticionGuardar())).thenReturn(getClienteGuardar());
        when(gestionarClientes.guardarCliente(getClienteGuardar())).thenReturn(getClienteGuardar());
        when(mapeadorDto.aRespuesta(getClienteGuardar())).thenReturn(getClienteRespuestaGuardar());

        //when
        mockMvc.perform(put("/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(getClientePeticionGuardar())))

                //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tipoDeDocumento").value("CC"))
                .andExpect(jsonPath("$.numeroDeIdentificacion").value("1000000001"))
                .andExpect(content().json(objectMapper.writeValueAsString(getClienteRespuestaGuardar())))
                .andDo(print());
    }
}