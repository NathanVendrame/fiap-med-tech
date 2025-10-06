package com.posfiap.controller;

import com.posfiap.security.JwtUtils;
import com.posfiap.usuario.*; // stubs gerados do proto
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @GrpcClient("grpc-usuario")
    private UsuarioServiceGrpc.UsuarioServiceBlockingStub usuarioStub;

    private final JwtUtils jwt;

    public AuthenticationController(JwtUtils jwt) { this.jwt = jwt; }

    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody Map<String, String> body) {
        var req = CreateUsuarioRequest.newBuilder()
                .setNome(body.get("nome"))
                .setCpf(body.get("cpf"))
                .setEmail(body.get("email"))
                .setSenha(body.get("senha"))
                .setTelefone(body.get("telefone"))
                .setTipoUsuario(TipoUsuario.valueOf(body.get("tipoUsuario")))
                .build();
        var resp = usuarioStub.createUsuario(req);
        return Map.of("usuarioId", resp.getUsuarioId(), "message", resp.getMessage());
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> body) {
        var vReq = ValidateLoginRequest.newBuilder()
                .setEmail(body.get("email"))
                .setSenha(body.get("senha"))
                .build();
        var vResp = usuarioStub.validateLogin(vReq);

        if (!vResp.getValido()) {
            throw new RuntimeException("Credenciais inválidas");
        }

        var u = vResp.getUsuario();
        String token = jwt.generateToken(u.getUsuarioId(), u.getEmail(), u.getTipoUsuario().name());
        return Map.of(
                "token", token,
                "usuarioId", u.getUsuarioId(),
                "role", u.getTipoUsuario().name(),
                "expiresIn", 86400
        );
    }
}
