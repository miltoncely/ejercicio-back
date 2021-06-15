package com.example.pragma.servicioclientes.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class Cliente {
    public interface Atributos{
        String TIPO_DOCUMENTO= "tipoDeDocumento";
        String NUMERO_IDENTIFICACION= "numeroDeIdentificacion";
        String ID_FOTO = Atributos.FOTO + "." + Imagen.Atributos.ID;
        String CONTENIDO_FOTO = Atributos.FOTO + "." + Imagen.Atributos.CONTENIDO;
        String FOTO = "foto";
    }
    private String tipoDeDocumento;
    private String numeroDeIdentificacion;
    private String nombres;
    private String apellidos;
    private String edad;
    private String ciudadDeNacimiento;
    private Imagen foto;
}
