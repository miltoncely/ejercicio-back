package com.example.pragma.servicioimagenes.infrastructure.entrypoints.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class IdentificadorImagenesList implements Serializable {
    private List<String> identificadores;
}
