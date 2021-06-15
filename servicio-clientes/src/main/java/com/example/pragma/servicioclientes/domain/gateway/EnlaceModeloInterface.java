package com.example.pragma.servicioclientes.domain.gateway;

import com.example.pragma.servicioclientes.domain.model.Cliente;

import java.util.List;

public interface EnlaceModeloInterface {
    Cliente guardarCliente(Cliente cliente);
    Cliente consultarCliente(String tipoDocumento,String numeroIdentificacion);
    Cliente actualizarCliente(Cliente cliente);

    List<Cliente> listarClientes();
    void eliminarCliente(String tipoDocumento,String numeroIdentificacion);
}
