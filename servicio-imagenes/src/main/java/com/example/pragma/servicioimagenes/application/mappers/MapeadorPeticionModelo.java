package com.example.pragma.servicioimagenes.application.mappers;

import com.example.pragma.servicioimagenes.domain.model.Imagen;
import com.example.pragma.servicioimagenes.infrastructure.entrypoints.dtos.ImagenPeticion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MapeadorPeticionModelo {
    @Mapping(target = Imagen.Atributes.ID, ignore = true)
    Imagen aModelo(ImagenPeticion  imagenPeticion);
}
