package com.example.pragma.servicioclientes.infrastructure.entrypoints;

import com.example.pragma.servicioclientes.infrastructure.entrypoints.dtos.ClientePeticion;
import com.example.pragma.servicioclientes.infrastructure.entrypoints.dtos.ClienteRespuesta;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
@CrossOrigin
public class RecursoClientes {

    private final ManejadorClientes manejadorClientes;

    @ApiOperation("Colsultar el cliente por identificacion")
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok"),
            @ApiResponse(code = 204, message = "No hay contenido para mostrar")
    })
    @GetMapping("/{tipoIdentificacion}/{numeroIdentificacion}")
    @ResponseStatus(HttpStatus.OK)
    public ClienteRespuesta consultarCliente(
            @ApiParam(value = "tipo de identificacion del cliente", required = true)
            @PathVariable String tipoIdentificacion,
            @ApiParam(value = "Identificador del cliente", required = true)
            @PathVariable String numeroIdentificacion
    ) {
        return manejadorClientes.consultarCliente(tipoIdentificacion,numeroIdentificacion);
    }

    @ApiOperation("Lista todos los clientes")
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok",response = List.class),
            @ApiResponse(code = 204, message = "No hay contenido para mostrar")})
    @GetMapping @ResponseStatus(HttpStatus.OK)
    public List<ClienteRespuesta> listarClientes() {
        return manejadorClientes.listarClientes();
    }

    @ApiOperation("Guardar cliente")
    @ApiResponses({
            @ApiResponse(code = 201, message = "El cliente fue creado en la bd"),
            @ApiResponse(code = 400, message = "El cliente ya esta registrado en la bd")
    })
    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public ClienteRespuesta guardarCliente(
            @Valid
            @ApiParam(value = "cliente en formato Json", required = true)
            @RequestBody ClientePeticion clientePeticion ){
        return manejadorClientes.guardarCliente(clientePeticion);
    }

    @ApiOperation("Actualiza el cliente")
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok"),
            @ApiResponse(code = 404, message = "La cliente no esta registrado")
    })
    @PutMapping @ResponseStatus(HttpStatus.OK)
    public ClienteRespuesta actualizarCliente(
            @ApiParam(value = "Cliente en formato Json", required = true)
            @RequestBody ClientePeticion clientePeticion) {
        return manejadorClientes.actualizarCliente(clientePeticion);
    }


    @ApiOperation("Elimina el cliente")
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok"),
            @ApiResponse(code = 404, message = "No se encuentra en la base de datos")
    })
    @DeleteMapping("/{tipo}/{identificacion}")
    @ResponseStatus(HttpStatus.OK)
    public void eliminarCliente(
            @ApiParam(value = "tipo de identificacion del cliente", required = true)
            @PathVariable String tipo,
            @ApiParam(value = "Identificador del cliente", required = true)
            @PathVariable String identificacion) {
        manejadorClientes.eliminarCliente(tipo, identificacion);
    }

    @GetMapping("/edad/{edad}")
    public List<ClienteRespuesta> listarClientesPorEdad(
            @PathVariable Integer edad
    ){
        return manejadorClientes.listarClientesPorEdad(edad);
    }
}
