package com.posfiap.service;

import com.posfiap.dto.CreateUsuarioResponseDTO;
import com.posfiap.dto.UsuarioDTO;
import com.posfiap.usuario.*;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @GrpcClient("grpc-usuario")
    private UsuarioServiceGrpc.UsuarioServiceBlockingStub usuarioServiceBlockingStub;

    public UsuarioDTO getUsuarioById(Long usuarioId) {
        GetUsuarioByIdRequest request = GetUsuarioByIdRequest.newBuilder()
                .setUsuarioId(usuarioId)
                .build();
        GetUsuarioByIdResponse response = usuarioServiceBlockingStub.getUsuarioById(request);

        return new UsuarioDTO(response.getUsuario());
    }

    public List<UsuarioDTO> listUsuario() {
        ListUsuarioRequest request = ListUsuarioRequest.newBuilder().build();
        ListUsuarioResponse response = usuarioServiceBlockingStub.listUsuario(request);

        return response.getUsuarioList().stream().map(UsuarioDTO::new).toList();
    }

    public CreateUsuarioResponseDTO createUsuario(UsuarioDTO usuarioDTO) {
        CreateUsuarioRequest request = CreateUsuarioRequest.newBuilder()
                .setNome(usuarioDTO.getNome())
                .setCpf(usuarioDTO.getCpf())
                .setEmail(usuarioDTO.getEmail())
                .setSenha(usuarioDTO.getSenha())
                .setTelefone(usuarioDTO.getTelefone())
                .setTipoUsuario(TipoUsuario.valueOf(usuarioDTO.getTipoUsuario()))
                .build();
        CreateUsuarioResponse response = usuarioServiceBlockingStub.createUsuario(request);

        return new CreateUsuarioResponseDTO(response.getUsuarioId(), response.getMessage());
    }
}
