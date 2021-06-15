package com.example.pragma.servicioclientes.infrastructure.adapters.mysql;

import com.example.pragma.servicioclientes.domain.model.Imagen;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "clientes")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ClienteEntidad {

    public interface Atributos{
        String TIPO_DOCUMENTO = "id.tipoDocumento";
        String NUMERO_IDENTIFICACION = "id.numeroDeIdentificacion";
        String ID_FOTO = "idFoto";
        String IMAGEN = "imagen";
    }

    @EmbeddedId
    private ClientePk id;
    private String nombres;
    private String apellidos;
    private String edad;
    private String ciudadDeNacimiento;
    private String idFoto;

    @Transient
    private Imagen imagen;
}
