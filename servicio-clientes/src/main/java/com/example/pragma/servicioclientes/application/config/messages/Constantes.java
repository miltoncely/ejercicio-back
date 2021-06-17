package com.example.pragma.servicioclientes.application.config.messages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:messages/api_error_messages")
public interface Constantes {
    String ERROR_VALIDACION_NUMERO_NEGATIVO = "error.validacion.numeroIdentificacion.negativo";
    String ERROR_VALIDACION_NUMERO_MENOR = "error.validacion.numeroIdentificacion.menorDelEsperado";
    String ERROR_VALIDACION_NUMERO_MAYOR = "error.validacion.numeroIdentificacion.mayorDelEsperado";

    String VALOR_IDENTIFICACION_MINIMO = "validacion.cliente.numeroIdentificacion.minimo";
    String VALOR_IDENTIFICACION_MAXIMO = "validacion.cliente.numeroIdentificacion.maximo";
}

