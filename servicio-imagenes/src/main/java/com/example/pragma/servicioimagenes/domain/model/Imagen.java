package com.example.pragma.servicioimagenes.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Imagen {
    public interface Atributes {
        String ID = "id";
        String CONTENIDO = "contenido";
    }

    private String id;
    private String contenido;
}
