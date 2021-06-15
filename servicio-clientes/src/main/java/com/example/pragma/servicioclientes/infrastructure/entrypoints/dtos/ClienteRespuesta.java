package com.example.pragma.servicioclientes.infrastructure.entrypoints.dtos;

import com.example.pragma.servicioclientes.domain.model.Imagen;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class ClienteRespuesta {
    public interface Atributos{
        String TIPO_DOCUMENTO= "tipoDeDocumento";
        String NUMERO_IDENTIFICACION= "numeroDeIdentificacion";
        String ID_FOTO = "foto.id";
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
