package com.posfiap.controller;

import com.posfiap.security.JwtUtils;
import com.posfiap.usuario.*;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
public class AuthenticationController {

    @GrpcClient("grpc-usuario")
    private UsuarioServiceGrpc.UsuarioServiceBlockingStub usuarioStub;

    private final JwtUtils jwt;

    public AuthenticationController(JwtUtils jwt) {
        this.jwt = jwt;
    }

    // === LOGIN ===
    @MutationMapping
    public AuthPayload login(@Argument("input") LoginInput input) {
        var vReq = ValidateLoginRequest.newBuilder()
                .setEmail(input.email())
                .setSenha(input.senha())
                .build();

        var vResp = usuarioStub.validateLogin(vReq);
        if (!vResp.getValido()) {
            throw new RuntimeException("Credenciais inválidas");
        }

        var u = vResp.getUsuario();
        String token = jwt.generateToken(Long.valueOf(u.getUsuarioId()), u.getEmail(), u.getTipoUsuario().name());
        return new AuthPayload(token, u.getUsuarioId(), u.getTipoUsuario().name(), 86400);
    }

    // === REGISTER ===
    @MutationMapping
    public RegisterPayload register(@Argument("input") RegisterInput input) {
        var req = CreateUsuarioRequest.newBuilder()
                .setNome(input.nome())
                .setCpf(input.cpf())
                .setEmail(input.email())
                .setSenha(input.senha())
                .setTelefone(input.telefone())
                .setTipoUsuario(TipoUsuario.valueOf(input.tipoUsuario().name()))
                .build();

        try {
            var resp = usuarioStub.createUsuario(req);

            // Caso o microserviço de usuário retorne ID 0 ou mensagem indicando duplicidade:
            if (resp.getUsuarioId() == 0 ||
                    resp.getMessage().toLowerCase().contains("já cadastrado")) {
                return new RegisterPayload(
                        resp.getUsuarioId(),
                        resp.getMessage(),
                        null,   // não gera token
                        null,
                        null
                );
            }

            // Usuário criado com sucesso → gera token normalmente
            var token = jwt.generateToken(
                    resp.getUsuarioId(),
                    input.email(),
                    input.tipoUsuario().name()
            );

            return new RegisterPayload(
                    resp.getUsuarioId(),
                    resp.getMessage(),
                    token,
                    input.tipoUsuario().name(),
                    86400
            );

        } catch (StatusRuntimeException e) {
            if (e.getStatus().getCode() == Status.ALREADY_EXISTS.getCode()) {
                return new RegisterPayload(
                        0L,
                        e.getStatus().getDescription(),
                        null,
                        null,
                        null
                );
            }
            throw e; // se for outro erro, propaga
        }
    }

    // === TYPES ===
    public record LoginInput(String email, String senha) {}
    public record AuthPayload(String token, Long usuarioId, String role, Integer expiresIn) {}
    public record RegisterInput(String nome, String cpf, String email, String senha, String telefone, TipoUsuario tipoUsuario) {}
    public record RegisterPayload(Long usuarioId, String message, String token, String role, Integer expiresIn) {}
}
