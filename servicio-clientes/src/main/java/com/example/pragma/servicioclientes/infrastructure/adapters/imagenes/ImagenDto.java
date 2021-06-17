package com.example.pragma.servicioclientes.infrastructure.adapters.imagenes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ImagenDto {
    private String id;
    private String contenido;
}
