package com.example.pragma.servicioclientes.infrastructure.entrypoints;

import com.example.pragma.servicioclientes.application.mappers.MapeadorDtoModelo;
import com.example.pragma.servicioclientes.domain.model.Cliente;
import com.example.pragma.servicioclientes.domain.usecase.GestionarClientesInterface;
import com.example.pragma.servicioclientes.infrastructure.entrypoints.dtos.ClientePeticion;
import com.example.pragma.servicioclientes.infrastructure.entrypoints.dtos.ClienteRespuesta;
import com.example.pragma.servicioclientes.infrastructure.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.example.pragma.servicioclientes.application.config.messages.constants.ApiErrorResponses.*;
import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

@Component
@RequiredArgsConstructor
public class ManejadorClientes {

    private final GestionarClientesInterface gestionarClientes;

    private final MapeadorDtoModelo mapeadorDto;

    private final MessageSource messageSource;

    public ClienteRespuesta consultarCliente(
            String tipoIdentificacion, String numeroIdentificacion
    ) {
        Cliente clienteBD = gestionarClientes.consultarCliente(tipoIdentificacion, numeroIdentificacion);
        if (clienteBD == null) {
            throw new ApiException(
                    HttpStatus.NOT_FOUND,
                    messageSource.getMessage(CLIENTE_NO_ENCONTRADO, null, null)
            );
        }
        return mapeadorDto.aRespuesta(clienteBD);
    }

    public List<ClienteRespuesta> listarClientes() {
        List<Cliente> clientesBD = gestionarClientes.listarClientes();
        if (clientesBD.isEmpty()) {
            throw new ApiException(
                    HttpStatus.NO_CONTENT,
                    messageSource.getMessage(CLIENTE_NO_HAY_REGISTROS, null, null)
            );
        }
        return mapeadorDto.aRespuestas(clientesBD);
    }

    public ClienteRespuesta guardarCliente(
            ClientePeticion clientePeticion) {

        if (existeElCliente(clientePeticion)) {
            throw new ApiException(
                    HttpStatus.BAD_REQUEST,
                    messageSource.getMessage(CLIENTE_YA_EXISTE, null, null)
            );
        }
        Cliente cliente = mapeadorDto.aModelo(clientePeticion);
        Cliente clienteCreado = gestionarClientes.guardarCliente(cliente);
        ClienteRespuesta respuesta = mapeadorDto.aRespuesta(clienteCreado);
        return respuesta;
    }

    public ClienteRespuesta actualizarCliente(
            ClientePeticion clientePeticion) {
        if (!existeElCliente(clientePeticion)) {
            throw new ApiException(
                    HttpStatus.NOT_FOUND,
                    messageSource.getMessage(CLIENTE_NO_ENCONTRADO, null, getLocale()));
        }
        Cliente cliente = mapeadorDto.aModelo(clientePeticion);
        Cliente clienteActualizado = gestionarClientes.actualizarCliente(cliente);
        ClienteRespuesta respuesta = mapeadorDto.aRespuesta(clienteActualizado);
        return respuesta;
    }

    public void eliminarCliente(
            String tipo, String identificacion) {
        Cliente clienteExistente = gestionarClientes.consultarCliente(
                tipo,
                identificacion);
        if (clienteExistente == null) {
            throw new ApiException(
                    HttpStatus.NOT_FOUND,
                    messageSource.getMessage(CLIENTE_NO_ENCONTRADO, null, null));
        }
        gestionarClientes.eliminarCliente(tipo, identificacion);
    }


    public List<ClienteRespuesta> listarClientesPorEdad(
            Integer edad
    ) {
        List<Cliente> clientesBD = gestionarClientes.listarClientesPorEdad(edad);
        if (clientesBD.isEmpty()) {
            throw new ApiException(
                    HttpStatus.NO_CONTENT,
                    messageSource.getMessage(CLIENTE_NO_HAY_REGISTROS, null, null)
            );
        }
        return mapeadorDto.aRespuestas(clientesBD);
    }

    private Boolean existeElCliente(ClientePeticion clientePeticion) {
        Cliente cliente = mapeadorDto.aModelo(clientePeticion);
        Cliente clienteExistente = gestionarClientes.consultarCliente(
                cliente.getTipoDeDocumento(),
                cliente.getNumeroDeIdentificacion());
        if (clienteExistente != null) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
