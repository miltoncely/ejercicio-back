package com.example.pragma.servicioclientes.domain.usecase;

import com.example.pragma.servicioclientes.domain.gateway.EnlaceModeloInterface;
import com.example.pragma.servicioclientes.domain.model.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class GestionarClientesTest {

    @MockBean
    EnlaceModeloInterface clienteRepositorio;

    @Autowired
    GestionarClientesInterface gestionarClientes;


    @Test
    void contextLoads(){
        when(clienteRepositorio.consultarCliente("CC","1000000001")).thenReturn(null);

        Cliente clienteExistente = gestionarClientes.consultarCliente("CC","1000000001");

        assertNull(clienteExistente);
        verify(clienteRepositorio, times(1)).consultarCliente("CC","1000000001");
    }
}