package com.posfiap.service;

import com.posfiap.service.handler.UsuarioRequestHandler;
import com.posfiap.usuario.*;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@AllArgsConstructor
@GrpcService
public class UsuarioService extends UsuarioServiceGrpc.UsuarioServiceImplBase {

    private final UsuarioRequestHandler usuarioRequestHandler;

    @Override
    public void createUsuario(CreateUsuarioRequest request, StreamObserver<CreateUsuarioResponse> responseObserver) {
        responseObserver.onNext(usuarioRequestHandler.createUsuario(request));
        responseObserver.onCompleted();
    }

    @Override
    public void listUsuario(ListUsuarioRequest request, StreamObserver<ListUsuarioResponse> responseObserver) {
        responseObserver.onNext(usuarioRequestHandler.getAllUsuarios());
        responseObserver.onCompleted();
    }

    @Override
    public void getUsuarioById(GetUsuarioByIdRequest request, StreamObserver<GetUsuarioByIdResponse> responseObserver) {
        responseObserver.onNext(usuarioRequestHandler.getUsuarioById(request));
        responseObserver.onCompleted();
    }
}
