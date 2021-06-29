package com.example.pragma.servicioclientes.domain.usecase;

import com.example.pragma.servicioclientes.domain.gateway.EnlaceModeloInterface;
import com.example.pragma.servicioclientes.domain.model.Cliente;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GestionarClientes implements GestionarClientesInterface{

    private final EnlaceModeloInterface clienteRepositorio;

    @Override
    public Cliente guardarCliente(Cliente cliente) {
        return clienteRepositorio.guardarCliente(cliente);
    }

    @Override
    public Cliente consultarCliente(String tipoDocumento, String numeroIdentificacion) {
        return clienteRepositorio.consultarCliente(tipoDocumento,numeroIdentificacion);
    }

    @Override
    public Cliente actualizarCliente(Cliente cliente) {
        return clienteRepositorio.actualizarCliente(cliente);
    }

    @Override
    public List<Cliente> listarClientes() {
        return clienteRepositorio.listarClientes();
    }

    @Override
    public void eliminarCliente(String tipoDocumento, String numeroIdentificacion) {
        clienteRepositorio.eliminarCliente(tipoDocumento,numeroIdentificacion);
    }

    @Override
    public List<Cliente> listarClientesPorEdad(Integer edad) {
        return clienteRepositorio.listarClientesPorEdad(edad);
    }
}
