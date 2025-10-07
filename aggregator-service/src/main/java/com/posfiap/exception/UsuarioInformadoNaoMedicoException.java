package com.posfiap.exception;

public class UsuarioInformadoNaoMedicoException extends RuntimeException {

    private static final String MENSAGEM = "Medico [id=%d] não é um medico";

    public UsuarioInformadoNaoMedicoException(Long usuarioId) {
        super(MENSAGEM.formatted(usuarioId));
    }
}
