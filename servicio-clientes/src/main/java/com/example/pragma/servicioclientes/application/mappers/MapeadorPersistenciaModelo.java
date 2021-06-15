package com.example.pragma.servicioclientes.application.mappers;

import com.example.pragma.servicioclientes.domain.model.Cliente;
import com.example.pragma.servicioclientes.infrastructure.adapters.mysql.ClienteEntidad;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MapeadorPersistenciaModelo {

    @Mappings({
            @Mapping(source = ClienteEntidad.Atributos.TIPO_DOCUMENTO, target = Cliente.Atributos.TIPO_DOCUMENTO),
            @Mapping(source = ClienteEntidad.Atributos.NUMERO_IDENTIFICACION, target = Cliente.Atributos.NUMERO_IDENTIFICACION),
            @Mapping(source = ClienteEntidad.Atributos.ID_FOTO, target = Cliente.Atributos.ID_FOTO),
            @Mapping(source = ClienteEntidad.Atributos.IMAGEN, target = Cliente.Atributos.FOTO)
    })
    Cliente aModelo(ClienteEntidad cliente);

    @InheritConfiguration
    List<Cliente> aModelos(List<ClienteEntidad> clientes);

    @InheritInverseConfiguration
    ClienteEntidad aPersistencia(Cliente cliente);

    @InheritInverseConfiguration
    List<ClienteEntidad> aPersistencias(List<Cliente> clientes);
}
