package com.posfiap.service.handler.exception;

import com.posfiap.exception.UsuarioNaoEncontradoException;
import io.grpc.Status;
import net.devh.boot.grpc.server.advice.GrpcAdvice;
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler;

@GrpcAdvice
public class ServiceExceptionHandler {

    @GrpcExceptionHandler(UsuarioNaoEncontradoException.class)
    public Status handleUsuarioNaoEncontrado(UsuarioNaoEncontradoException exception) {
        return Status.INVALID_ARGUMENT.withDescription(exception.getMessage());
    }
}
