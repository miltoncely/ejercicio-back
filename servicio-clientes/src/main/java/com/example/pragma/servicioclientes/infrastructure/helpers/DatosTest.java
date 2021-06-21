package com.example.pragma.servicioclientes.infrastructure.helpers;

import com.example.pragma.servicioclientes.domain.model.Cliente;
import com.example.pragma.servicioclientes.domain.model.Imagen;
import com.example.pragma.servicioclientes.infrastructure.entrypoints.dtos.ClientePeticion;
import com.example.pragma.servicioclientes.infrastructure.entrypoints.dtos.ClienteRespuesta;

import java.util.ArrayList;
import java.util.List;

public class DatosTest {

    public static Cliente getCliente() {
        return Cliente.builder()
                .tipoDeDocumento("CC")
                .numeroDeIdentificacion("1000000000")
                .nombres("Milton Cely")
                .apellidos("Cely Carrascal")
                .ciudadDeNacimiento("Cucuta")
                .edad("21")
                .foto(Imagen.builder().id("idfoto").contenido("contenidobase64").build())
                .build();
    }

    public static ClienteRespuesta getClienteRespuesta() {
        return ClienteRespuesta.builder()
                .tipoDeDocumento("CC")
                .numeroDeIdentificacion("1000000000")
                .nombres("Milton Cely")
                .apellidos("Cely Carrascal")
                .ciudadDeNacimiento("Cucuta")
                .edad("21")
                .foto(Imagen.builder().id("idfoto").contenido("contenidobase64").build())
                .build();
    }

    public static ClientePeticion getClientePeticionGuardar() {
        return ClientePeticion.builder()
                .tipoDeDocumento("CC")
                .numeroDeIdentificacion(1000000001L)
                .nombres("Milton Alexander")
                .apellidos("Cely Carrasca")
                .build();
    }

    public static Cliente getClienteGuardar() {
        return Cliente.builder()
                .tipoDeDocumento("CC")
                .numeroDeIdentificacion("1000000001")
                .nombres("Milton Alexander")
                .apellidos("Cely Carrasca")
                .build();

    }

    public static ClienteRespuesta getClienteRespuestaGuardar(){
        return ClienteRespuesta.builder()
                .tipoDeDocumento("CC")
                .numeroDeIdentificacion("1000000001")
                .nombres("Milton Alexander")
                .apellidos("Cely Carrasca")
                .build();
    }

    public static List<Cliente> getClientesConsultadosPorEdad(){

        Cliente cliente1 = Cliente.builder()
                .tipoDeDocumento("CC")
                .numeroDeIdentificacion("1000000001")
                .nombres("Milton Alexander")
                .apellidos("Cely Carrascal")
                .edad("32")
                .build();

        Cliente cliente2 = Cliente.builder()
                .tipoDeDocumento("CC")
                .numeroDeIdentificacion("1000000002")
                .nombres("Milton Alexander")
                .apellidos("Cely Carrasca")
                .edad("34")
                .build();

        return List.of(cliente1,cliente2);
    }

    public static List<ClienteRespuesta> getClientesRespuestaConsultadosPorEdad(){

        ClienteRespuesta cliente1 = ClienteRespuesta.builder()
                .tipoDeDocumento("CC")
                .numeroDeIdentificacion("1000000001")
                .nombres("Milton Alexander")
                .apellidos("Cely Carrascal")
                .edad("32")
                .build();

        ClienteRespuesta cliente2 = ClienteRespuesta.builder()
                .tipoDeDocumento("CC")
                .numeroDeIdentificacion("1000000002")
                .nombres("Milton Alexander")
                .apellidos("Cely Carrasca")
                .edad("34")
                .build();

        return List.of(cliente1,cliente2);
    }

    public static List<Cliente> getListadoClientes(){
        List<Cliente> listadoDeClientes = new ArrayList<>(getClientesConsultadosPorEdad());

        Cliente cliente3 = Cliente.builder()
                .tipoDeDocumento("CC")
                .numeroDeIdentificacion("1000000002")
                .nombres("Milton Alexander")
                .apellidos("Cely Carrasca")
                .edad("2")
                .build();

        listadoDeClientes.add(cliente3);

        return listadoDeClientes;
    }

    public static List<ClienteRespuesta> getListadoClientesRespuesta(){
        List<ClienteRespuesta> listadoDeClientes = new ArrayList<>(getClientesRespuestaConsultadosPorEdad());

        ClienteRespuesta cliente3 = ClienteRespuesta.builder()
                .tipoDeDocumento("CC")
                .numeroDeIdentificacion("1000000002")
                .nombres("Milton Alexander")
                .apellidos("Cely Carrasca")
                .edad("2")
                .build();

        listadoDeClientes.add(cliente3);

        return listadoDeClientes;
    }
}
