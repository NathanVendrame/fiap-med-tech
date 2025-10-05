package com.posfiap.service.handler.exception;

import com.posfiap.exception.AgendamentoNaoEncontradoException;
import com.posfiap.exception.FormatoDataInvalidoException;
import io.grpc.Status;
import net.devh.boot.grpc.server.advice.GrpcAdvice;
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler;

@GrpcAdvice
public class ServiceExceptionHandler {

    @GrpcExceptionHandler(AgendamentoNaoEncontradoException.class)
    public Status handleUsuarioNaoEncontrado(AgendamentoNaoEncontradoException exception) {
        return Status.INVALID_ARGUMENT.withDescription(exception.getMessage());
    }

    @GrpcExceptionHandler(FormatoDataInvalidoException.class)
    public Status handleFormatoDataInvalidoException(FormatoDataInvalidoException exception) {
        return Status.INVALID_ARGUMENT.withDescription(exception.getMessage());
    }
}
