package com.example.pragma.servicioclientes.infrastructure.entrypoints.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class ClientePeticion implements Serializable {
    public interface Atributos{
        String TIPO_DOCUMENTO= "tipoDeDocumento";
        String NUMERO_IDENTIFICACION= "numeroDeIdentificacion";
        String CONTENIDO_FOTO = "foto";
    }
    private String tipoDeDocumento;
    private String numeroDeIdentificacion;
    private String nombres;
    private String apellidos;
    private String edad;
    private String ciudadDeNacimiento;
    private String foto;
}
