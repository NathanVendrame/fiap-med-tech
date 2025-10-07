package com.posfiap.exception;

public class UsuarioInformadoNaoPacienteException extends RuntimeException {

    private static final String MENSAGEM = "Paciente [id=%d] não é um paciente";

    public UsuarioInformadoNaoPacienteException(Long usuarioId) {
        super(MENSAGEM.formatted(usuarioId));
    }
}
