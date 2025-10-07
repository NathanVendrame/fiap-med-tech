package com.posfiap.service;

import com.posfiap.repository.UsuarioRepository;
import com.posfiap.service.handler.UsuarioRequestHandler;
import com.posfiap.usuario.*;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@AllArgsConstructor
@GrpcService
public class UsuarioService extends UsuarioServiceGrpc.UsuarioServiceImplBase {

    private final UsuarioRequestHandler usuarioRequestHandler;

    @Autowired
    private UsuarioRepository usuarioRepository;

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

    private UsuarioProto mapToProto(com.posfiap.model.entity.Usuario usuario) {
        return UsuarioProto.newBuilder()
                .setUsuarioId(Long.parseLong(usuario.getId().toString()))
                .setNome(usuario.getNome())
                .setCpf(usuario.getCpf())
                .setEmail(usuario.getEmail())
                .setSenha(usuario.getSenha())
                .setTelefone(usuario.getTelefone())
                .setTipoUsuario(TipoUsuario.valueOf(usuario.getTipoUsuario().name())) // ENUM -> String
                .build();
    }

    @Override
    public void validateLogin(ValidateLoginRequest req, StreamObserver<ValidateLoginResponse> obs) {
        var enc = new BCryptPasswordEncoder();
        var opt = usuarioRepository.findByEmail(req.getEmail());

        if (opt.isEmpty() || !enc.matches(req.getSenha(), opt.get().getSenha())) {
            obs.onNext(ValidateLoginResponse.newBuilder()
                    .setValido(false).setMessage("Credenciais inválidas").build());
            obs.onCompleted();
            return;
        }

        var u = opt.get();
        obs.onNext(ValidateLoginResponse.newBuilder()
                .setValido(true)
                .setUsuario(mapToProto(u))
                .setMessage("ok")
                .build());
        obs.onCompleted();
    }

}
