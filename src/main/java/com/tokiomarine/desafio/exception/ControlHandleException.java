package com.tokiomarine.desafio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControlHandleException {


    @ExceptionHandler(TransacaoException.class)
    public ResponseEntity<?> transacaoException(TransacaoException ex) {
        final var builder = MessageException.builder().message(ex.getMessage())
            .status(HttpStatus.NOT_ACCEPTABLE)
            .build();
        return ResponseEntity.status(builder.getStatus()).body(builder);
    }
}
