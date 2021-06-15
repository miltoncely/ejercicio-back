package com.example.pragma.servicioclientes.infrastructure.adapters.mysql;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class ClientePk implements Serializable {
    private String tipoDocumento;
    private String numeroDeIdentificacion;
}
