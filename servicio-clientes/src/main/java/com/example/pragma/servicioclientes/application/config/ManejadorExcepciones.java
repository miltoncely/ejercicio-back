package com.example.pragma.servicioclientes.application.config;

import com.example.pragma.servicioclientes.infrastructure.exception.ApiExcepcion;
import com.example.pragma.servicioclientes.infrastructure.exception.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ManejadorExcepciones {

    @ExceptionHandler(value = {
            HttpClientErrorException.class
    })
    public ResponseEntity<Object> handleHttpClientErrorException(HttpClientErrorException e){
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        ApiExcepcion apiException = ApiExcepcion.builder()
                .causa(e.getMessage())
                .estatus(notFound.value())
                .httpError(notFound)
                .timestamp(ZonedDateTime.now())
                .build();

        return new ResponseEntity<>(apiException,notFound);
    }

    @ExceptionHandler(value = {
            ApiException.class
    })
    public ResponseEntity<Object> handleClienteYaExisteExcepcion(ApiException e){
        HttpStatus httpStatus = e.getHttpStatus();
        ApiExcepcion apiException = ApiExcepcion.builder()
                .causa(e.getMessage())
                .estatus(httpStatus.value())
                .httpError(httpStatus)
                .timestamp(ZonedDateTime.now())
                .build();

        return new ResponseEntity<>(apiException,httpStatus);
    }
}
