package com.example.pragma.servicioimagenes.application.mappers;

import com.example.pragma.servicioimagenes.domain.model.Imagen;
import com.example.pragma.servicioimagenes.infrastructure.adapters.mongo.ImagenPersistenteMongo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MapeadorPersistenciaModelo {
    Imagen aModelo(ImagenPersistenteMongo imagenPersistenteMongo);
    ImagenPersistenteMongo aPersistencia(Imagen imagen);
    List<Imagen> aModelos(List<ImagenPersistenteMongo> imagenPersistenteMongo);
    List<ImagenPersistenteMongo> aPersistencias(List<Imagen> imagen);
}
