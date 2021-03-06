package com.example.pragma.servicioclientes.application.config;

import com.example.pragma.servicioclientes.infrastructure.exception.ApiExcepcion;
import com.example.pragma.servicioclientes.infrastructure.exception.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

import java.net.ConnectException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.example.pragma.servicioclientes.application.config.messages.constants.ApiErrorResponses.SERVICIO_NO_DISPONIBLE;

@ControllerAdvice
public class ManejadorExcepciones {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(value = {
            HttpClientErrorException.class
    })
    public ResponseEntity<Object> handleHttpClientErrorException(HttpClientErrorException e){
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ApiExcepcion apiException = ApiExcepcion.builder()
                .causas(List.of(e.getMessage()))
                .estatus(httpStatus.value())
                .httpError(httpStatus)
                .timestamp(ZonedDateTime.now())
                .build();

        return new ResponseEntity<>(apiException,httpStatus);
    }

    @ExceptionHandler(value = {
            ApiException.class
    })
    public ResponseEntity<Object> handleApiException(ApiException e){
        HttpStatus httpStatus = e.getHttpStatus();
        ApiExcepcion apiException = ApiExcepcion.builder()
                .causas(List.of(e.getMessage()))
                .estatus(httpStatus.value())
                .httpError(httpStatus)
                .timestamp(ZonedDateTime.now())
                .build();

        return new ResponseEntity<>(apiException,httpStatus);
    }

    @ExceptionHandler(value = {
            MethodArgumentNotValidException.class
    })
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        BindingResult b= e.getBindingResult();
        List<ObjectError> errores = b.getAllErrors();
        List<String> mensajesDeError = new ArrayList<>();

        for (ObjectError erro: errores) {
            mensajesDeError.add(erro.getDefaultMessage());
        }

        ApiExcepcion apiException = ApiExcepcion.builder()
                .causas(mensajesDeError)
                .estatus(httpStatus.value())
                .httpError(httpStatus)
                .timestamp(ZonedDateTime.now())
                .build();

        return new ResponseEntity<>(apiException,httpStatus);
    }

    @ExceptionHandler(value = {
            ConnectException.class
    })
    @ResponseBody
    @ResponseStatus(code = HttpStatus.SERVICE_UNAVAILABLE)
    public ApiExcepcion handleMethodArgumentNotValidException(ConnectException e){
        HttpStatus httpStatus = HttpStatus.SERVICE_UNAVAILABLE;
        ApiExcepcion apiException = ApiExcepcion.builder()
                .causas(List.of(messageSource.getMessage(SERVICIO_NO_DISPONIBLE,null,null)))
                .estatus(httpStatus.value())
                .httpError(httpStatus)
                .timestamp(ZonedDateTime.now())
                .build();
        return apiException;
    }
}
