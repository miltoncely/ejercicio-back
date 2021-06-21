package com.example.pragma.servicioclientes.infrastructure.entrypoints.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientePeticion implements Serializable {
    public interface Atributos {
        String TIPO_DOCUMENTO = "tipoDeDocumento";
        String CONTENIDO_FOTO = "foto";
    }

    @NotBlank(message = "El tipo de documento no debe estar vacio")
    private String tipoDeDocumento;

    @DecimalMin(value = "1000000000", message = "El numero de identificacion es menor que el esperado")
    @DecimalMax(value = "9999999999", message = "El numero de identificacion excede el maximo permitido")
    private Long numeroDeIdentificacion;

    @NotBlank(message = "El nombre no debe estar vacio")
    private String nombres;

    @NotBlank(message = "El apellido no debe estar vacio")
    private String apellidos;

    @DecimalMin(value = "7", message = "La edad del cliente debe ser mayor que 7")
    private Integer edad;

    private String ciudadDeNacimiento;

    private String foto;
}
