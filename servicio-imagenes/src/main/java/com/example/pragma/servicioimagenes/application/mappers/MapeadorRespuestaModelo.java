package com.example.pragma.servicioimagenes.application.mappers;

import com.example.pragma.servicioimagenes.domain.model.Imagen;
import com.example.pragma.servicioimagenes.infrastructure.adapters.mongo.ImagenPersistenteMongo;
import com.example.pragma.servicioimagenes.infrastructure.entrypoints.dtos.ImagenRespuesta;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MapeadorRespuestaModelo {
    Imagen aModelo(ImagenRespuesta imagenRespuesta);
    ImagenRespuesta aRespuesta(Imagen imagen);
    List<Imagen> aModelos(List<ImagenRespuesta> imagenPersistenteMongo);
    List<ImagenRespuesta> aRespuestas(List<Imagen> imagen);
}
