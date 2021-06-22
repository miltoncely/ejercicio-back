package com.example.pragma.servicioimagenes.infrastructure.adapters;

import com.example.pragma.servicioimagenes.application.mappers.MapeadorPersistenciaModelo;
import com.example.pragma.servicioimagenes.domain.gateway.EnlaceModeloInterface;
import com.example.pragma.servicioimagenes.domain.model.Imagen;
import com.example.pragma.servicioimagenes.infrastructure.adapters.mongo.ImagenMongoDB;
import com.example.pragma.servicioimagenes.infrastructure.adapters.mongo.ImagenPersistenteMongo;
import com.example.pragma.servicioimagenes.infrastructure.exceptions.NotFountException;
import com.google.common.collect.ImmutableList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ImagenRepositorio implements EnlaceModeloInterface {

    private final ImagenMongoDB imagenMongoDB;
    private final MapeadorPersistenciaModelo mapeador;

    @Override
    public Imagen guardarImagen(Imagen imagen) {
        ImagenPersistenteMongo imagenDb = imagenMongoDB.save(mapeador.aPersistencia(imagen));
        return mapeador.aModelo(imagenDb);
    }

    @Override
    public Imagen consultarImagen(String id) {
        ImagenPersistenteMongo imagenDb = imagenMongoDB.findById(id).orElse(null);
        return mapeador.aModelo(imagenDb);
    }

    @Override
    public Imagen actualizarImagen(String identificacion, Imagen imagen) {
        Boolean existe = imagenMongoDB.existsById(identificacion);
        ImagenPersistenteMongo imagenDb = null;
        if(existe){
            imagen.setId(identificacion);
            imagenDb = imagenMongoDB.save(mapeador.aPersistencia(imagen));
        }
        return mapeador.aModelo(imagenDb);
    }

    @Override
    public List<Imagen> listarImagenes() {
        List<ImagenPersistenteMongo> imagenesDb = imagenMongoDB.findAll();
        return mapeador.aModelos(imagenesDb);
    }

    @Override
    public void eliminarImagen(String identificador){
        Optional<ImagenPersistenteMongo> imagenExistente = imagenMongoDB.findById(identificador);
        if(!imagenExistente.isPresent()){
            throw new NotFountException();
        }else {
            imagenMongoDB.delete(imagenExistente.get());
        }
    }

    @Override
    public List<Imagen> consultarImagenes(List<String> identificaciones) {
        List<ImagenPersistenteMongo> imagenesDb = ImmutableList.copyOf(imagenMongoDB.findAllById(identificaciones));
        return mapeador.aModelos(imagenesDb);
    }
}
