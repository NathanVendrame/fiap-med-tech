package com.posfiap.exception;

public class UsuarioNaoEncontradoException extends RuntimeException {

    private static final String MENSAGEM = "Usuário [id=%d] nao encontrado";

    public UsuarioNaoEncontradoException(Long usuarioId) {
        super(MENSAGEM.formatted(usuarioId));
    }
}
