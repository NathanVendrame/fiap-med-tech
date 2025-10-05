package com.posfiap.exception;

public class FormatoDataInvalidoException extends RuntimeException {

    private static final String MENSAGEM = "O formato da data informada esta invalido [%s], utilize o formato: 'aaaa-mm-dd'.";

    public FormatoDataInvalidoException(String dataInvalida) {
        super(MENSAGEM.formatted(dataInvalida));
    }
}
