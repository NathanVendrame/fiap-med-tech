package com.posfiap.service;

import com.posfiap.agendamento.Usuario;
import com.posfiap.agendamento.UsuarioRequest;
import com.posfiap.agendamento.UsuarioServiceGrpc;
import io.grpc.stub.StreamObserver;

public class UsuarioService extends UsuarioServiceGrpc.UsuarioServiceImplBase {

    @Override
    public void consultarUsuario(UsuarioRequest request, StreamObserver<Usuario> responseObserver) {
        Usuario paciente = Usuario.newBuilder()
                .setId(1)
                .setNome("Paciente 1")
                .setEmail("paciente@teste.com")
                .setTipoUsuario(com.posfiap.agendamento.TipoUsuario.PACIENTE)
                .build();

        responseObserver.onNext(paciente);
        responseObserver.onCompleted();
    }
}
