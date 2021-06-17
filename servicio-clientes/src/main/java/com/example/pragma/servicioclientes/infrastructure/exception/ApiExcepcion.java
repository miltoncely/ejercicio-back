package com.example.pragma.servicioclientes.infrastructure.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
import java.util.List;

@Getter @AllArgsConstructor @Builder
public class ApiExcepcion {
    private final ZonedDateTime timestamp;
    private final List<String> causas;
    private final Integer estatus;
    private final HttpStatus httpError;
}
