package com.example.pragma.servicioimagenes.application.config;

import com.example.pragma.servicioimagenes.infrastructure.exceptions.ApiExcepcion;
import com.example.pragma.servicioimagenes.infrastructure.exceptions.NotFountException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ManejadorExcepciones {

    @ExceptionHandler(value = {
            NotFountException.class
    })
    public ResponseEntity<Object> handleApiRequestException(NotFountException e){
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        ApiExcepcion apiException = ApiExcepcion.builder()
                .causa(e.getMessage())
                .estatus(notFound.value())
                .httpError(notFound)
                .timestamp(ZonedDateTime.now())
                .build();

        return new ResponseEntity<>(apiException,notFound);
    }
}
