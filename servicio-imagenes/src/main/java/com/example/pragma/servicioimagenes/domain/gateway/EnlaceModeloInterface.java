package com.example.pragma.servicioimagenes.domain.gateway;

import com.example.pragma.servicioimagenes.domain.model.Imagen;

import java.util.List;

public interface EnlaceModeloInterface {
    Imagen guardarImagen(Imagen imagen);
    Imagen consultarImagen(String id);
    Imagen actualizarImagen(String identificacion, Imagen imagen);
    List<Imagen> listarImagenes();
    void eliminarImagen(String identificador);

    List<Imagen> consultarImagenes(List<String> identificaciones);
}
