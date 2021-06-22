package com.example.pragma.servicioimagenes.domain.usecase;

import com.example.pragma.servicioimagenes.domain.model.Imagen;
import com.example.pragma.servicioimagenes.domain.gateway.EnlaceModeloInterface;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GestionarImagenes implements GestionarImagenesInterface{

    private final EnlaceModeloInterface imagenRepositorio;

    @Override
    public Imagen guardarImagen(Imagen imagen) {
        return imagenRepositorio.guardarImagen(imagen);
    }

    @Override
    public Imagen consultarImagen(String identificacion) {
        return imagenRepositorio.consultarImagen(identificacion);
    }

    @Override
    public List<Imagen> consultarImagenes(List<String> identificaciones) {
        return imagenRepositorio.consultarImagenes(identificaciones);
    }

    @Override
    public Imagen actualizarContenido(String identificacion, Imagen imagen) {
        return imagenRepositorio.actualizarImagen(identificacion,imagen);
    }

    @Override
    public List<Imagen> listarImagenes() {
        return imagenRepositorio.listarImagenes();
    }

    @Override
    public void eliminarImagen(String identificador) {
        imagenRepositorio.eliminarImagen(identificador);
    }
}
