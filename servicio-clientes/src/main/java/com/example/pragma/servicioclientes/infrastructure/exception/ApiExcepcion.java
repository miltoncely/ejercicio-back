package com.example.pragma.servicioclientes.infrastructure.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Getter @AllArgsConstructor @Builder
public class ApiExcepcion {
    private final ZonedDateTime timestamp;
    private final String causa;
    private final Integer estatus;
    private final HttpStatus httpError;
}
