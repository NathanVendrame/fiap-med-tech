package com.posfiap.exception;

public class AgendamentoNaoEncontradoException extends RuntimeException {

    private static final String MENSAGEM = "Agendamento [id=%d] nao encontrado";

    public AgendamentoNaoEncontradoException(Long agendamentoId) {
        super(MENSAGEM.formatted(agendamentoId));
    }
}
