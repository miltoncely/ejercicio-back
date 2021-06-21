package com.example.pragma.servicioclientes.domain.usecase;

import com.example.pragma.servicioclientes.domain.model.Cliente;

import java.util.List;

public interface GestionarClientesInterface {
    Cliente guardarCliente(Cliente cliente);
    Cliente consultarCliente(String tipoDocumento,String numeroIdentificacion);
    Cliente actualizarCliente(Cliente cliente);

    List<Cliente> listarClientes();
    void eliminarCliente(String tipoDocumento,String numeroIdentificacion);

    List<Cliente> listarClientesPorEdad(Integer edad);
}
