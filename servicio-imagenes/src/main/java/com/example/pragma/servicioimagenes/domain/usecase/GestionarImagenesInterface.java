package com.example.pragma.servicioimagenes.domain.usecase;

import com.example.pragma.servicioimagenes.domain.model.Imagen;

import java.util.List;

public interface GestionarImagenesInterface {
    Imagen guardarImagen(Imagen imagen);
    Imagen consultarImagen(String identificacion);
    List<Imagen> consultarImagenes(List<String> identificaciones);
    Imagen actualizarContenido(String identificacion,Imagen imagen);
    List<Imagen> listarImagenes();
    void eliminarImagen(String identificador);
}
