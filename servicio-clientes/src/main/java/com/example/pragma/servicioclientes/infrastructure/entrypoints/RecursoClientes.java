package com.example.pragma.servicioclientes.infrastructure.entrypoints;

import com.example.pragma.servicioclientes.application.mappers.MapeadorDtoModelo;
import com.example.pragma.servicioclientes.domain.model.Cliente;
import com.example.pragma.servicioclientes.domain.usecase.GestionarClientesInterface;
import com.example.pragma.servicioclientes.infrastructure.entrypoints.dtos.ClientePeticion;
import com.example.pragma.servicioclientes.infrastructure.entrypoints.dtos.ClienteRespuesta;
import com.example.pragma.servicioclientes.infrastructure.exception.ApiException;
import com.sun.istack.NotNull;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class RecursoClientes {

    @Autowired
    private GestionarClientesInterface gestionarClientes;
    @Autowired
    private MapeadorDtoModelo mapeadorDto;

    @ApiOperation("Colsultar el cliente por identificacion")
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok"),
            @ApiResponse(code = 204, message = "No hay contenido para mostrar")
    })
    @GetMapping("/{tipoIdentificacion}/{numeroIdentificacion}")
    public ResponseEntity<ClienteRespuesta> consultarCliente(
            @ApiParam(value = "tipo de identificacion del cliente", required = true)
            @PathVariable String tipoIdentificacion,
            @ApiParam(value = "Identificador del cliente", required = true)
            @PathVariable String numeroIdentificacion
    ) {
        Cliente clienteBD = gestionarClientes.consultarCliente(tipoIdentificacion, numeroIdentificacion);
        if (clienteBD == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(mapeadorDto.aRespuesta(clienteBD));
    }

    @ApiOperation("Lista todos los clientes")
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok"),
            @ApiResponse(code = 204, message = "No hay contenido para mostrar")})
    @GetMapping
    public ResponseEntity<List<ClienteRespuesta>> listarClientes() {
        List<Cliente> clientesBD = gestionarClientes.listarClientes();
        if (clientesBD.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(mapeadorDto.aRespuestas(clientesBD));
    }

    @ApiOperation("Guardar cliente")
    @ApiResponses({
            @ApiResponse(code = 201, message = "El cliente fue creado en la bd"),
            @ApiResponse(code = 400, message = "El cliente ya esta registrado en la bd")
    })
    @PostMapping
    public ResponseEntity<ClienteRespuesta> guardarCliente(
            @ApiParam(value = "cliente en formato Json", required = true)
            @RequestBody ClientePeticion clientePeticion) {
        if (existeElCliente(clientePeticion)) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "El cliente ya existe");
        }
        Cliente cliente = mapeadorDto.aModelo(clientePeticion);
        Cliente clienteCreado = gestionarClientes.guardarCliente(cliente);
        ClienteRespuesta respuesta = mapeadorDto.aRespuesta(clienteCreado);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @ApiOperation("Actualiza el cliente")
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok"),
            @ApiResponse(code = 404, message = "La cliente no esta registrado")
    })
    @PutMapping
    public ResponseEntity<ClienteRespuesta> actualizarCliente(
            @ApiParam(value = "Cliente en formato Json", required = true)
            @RequestBody ClientePeticion clientePeticion) {

        Cliente cliente = mapeadorDto.aModelo(clientePeticion);
        Cliente imagenActualizada = gestionarClientes.actualizarCliente(cliente);
        ClienteRespuesta respuesta = mapeadorDto.aRespuesta(imagenActualizada);

        if (imagenActualizada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }


    @ApiOperation("Elimina el cliente")
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok"),
            @ApiResponse(code = 404, message = "No se encuentra en la base de datos")
    })
    @DeleteMapping("/{tipo}/{identificacion}")
    public ResponseEntity eliminarCliente(
            @ApiParam(value = "tipo de identificacion del cliente", required = true)
            @PathVariable String tipo,
            @ApiParam(value = "Identificador del cliente", required = true)
            @PathVariable String identificacion) {

        gestionarClientes.eliminarCliente(tipo, identificacion);
        return ResponseEntity.ok().build();
    }

    private Boolean existeElCliente(@NonNull ClientePeticion clientePeticion) {
        Cliente clienteExistente = gestionarClientes.consultarCliente(
                clientePeticion.getTipoDeDocumento(),
                clientePeticion.getNumeroDeIdentificacion());
        if (clienteExistente != null) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
