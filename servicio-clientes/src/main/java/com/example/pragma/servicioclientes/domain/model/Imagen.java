package com.example.pragma.servicioclientes.domain.model;

import com.example.pragma.servicioclientes.infrastructure.entrypoints.dtos.ClientePeticion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Imagen {
    public interface Atributos{
        String ID = "id";
        String CONTENIDO = "contenido";
    }
    private String id;
    private String contenido;
}
