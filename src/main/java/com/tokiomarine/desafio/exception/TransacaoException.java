package com.tokiomarine.desafio.exception;

public class TransacaoException extends RuntimeException {

    public TransacaoException(String message) {
        super(message);
    }

    public TransacaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
