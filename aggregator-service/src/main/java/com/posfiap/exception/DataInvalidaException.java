package com.posfiap.exception;

public class DataInvalidaException extends RuntimeException {
    public DataInvalidaException(String data) {
        super("Data em formato inválido: " + data + ". Use o padrão yyyy-MM-dd.");
    }
}
