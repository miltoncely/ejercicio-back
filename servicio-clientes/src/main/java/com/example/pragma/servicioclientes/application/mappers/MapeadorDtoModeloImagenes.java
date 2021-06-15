package com.example.pragma.servicioclientes.application.mappers;

import com.example.pragma.servicioclientes.domain.model.Imagen;
import com.example.pragma.servicioclientes.infrastructure.adapters.imagenes.ImagenDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapeadorDtoModeloImagenes {
    Imagen AModelo(ImagenDto imagenDto);
    ImagenDto ADto(Imagen imagen);
}
