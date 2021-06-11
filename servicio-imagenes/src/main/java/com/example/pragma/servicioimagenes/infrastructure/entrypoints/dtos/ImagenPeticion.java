package com.example.pragma.servicioimagenes.infrastructure.entrypoints.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ImagenPeticion implements Serializable {
    private String contenido;
}
