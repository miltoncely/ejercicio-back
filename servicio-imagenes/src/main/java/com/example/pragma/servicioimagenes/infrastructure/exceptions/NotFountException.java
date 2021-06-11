package com.example.pragma.servicioimagenes.infrastructure.exceptions;

public class NotFountException extends RuntimeException{
    public NotFountException() {
        super("El recurso solicitado no fue encontrado");
    }
}
