package com.example.pragma.servicioclientes.application.mappers;

import com.example.pragma.servicioclientes.domain.model.Cliente;
import com.example.pragma.servicioclientes.infrastructure.entrypoints.dtos.ClientePeticion;
import com.example.pragma.servicioclientes.infrastructure.entrypoints.dtos.ClienteRespuesta;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MapeadorDtoModelo {
    @Mappings({
            @Mapping(source = ClientePeticion.Atributos.CONTENIDO_FOTO, target = Cliente.Atributos.CONTENIDO_FOTO)
    })
    Cliente aModelo(ClientePeticion clientePeticion);

    @InheritConfiguration
    List<Cliente> aModelos(List<ClientePeticion> ClientePeticion);


    ClienteRespuesta aRespuesta(Cliente cliente);
    List<ClienteRespuesta> aRespuestas(List<Cliente> clientes);
}
