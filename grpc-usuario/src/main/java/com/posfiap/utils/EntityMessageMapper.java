package com.posfiap.utils;

import com.posfiap.model.entity.Usuario;
import com.posfiap.model.enums.TipoUsuarioEnum;
import com.posfiap.usuario.*;

import java.util.List;

public class EntityMessageMapper {

    public static Usuario toEntity(CreateUsuarioRequest usuarioRequest) {
        return new Usuario(
                usuarioRequest.getNome(),
                usuarioRequest.getCpf(),
                usuarioRequest.getEmail(),
                usuarioRequest.getSenha(),
                usuarioRequest.getTelefone(),
                TipoUsuarioEnum.valueOf(usuarioRequest.getTipoUsuario().name())
        );
    }
    public static GetUsuarioByIdResponse toGetUsuarioByIdResponse(Usuario usuario) {
        return GetUsuarioByIdResponse.newBuilder()
                .setUsuario(toUsuarioProto(usuario))
                .build();
    }

    public static ListUsuarioResponse toListUsuarioResponse(List<Usuario> usuarios) {
        return ListUsuarioResponse.newBuilder().addAllUsuario(
                usuarios.stream()
                        .map(EntityMessageMapper::toUsuarioProto)
                        .toList())
                .build();
    }

    public static CreateUsuarioResponse toCreateUsuarioResponse(Usuario usuario) {
        return CreateUsuarioResponse.newBuilder()
                .setUsuarioId(usuario.getId())
                .setMessage("Usuário " + usuario.getNome() + " criado com sucesso")
                .build();
    }

    private static UsuarioProto toUsuarioProto(Usuario usuario) {
        return UsuarioProto.newBuilder()
                .setUsuarioId(usuario.getId())
                .setNome(usuario.getNome())
                .setCpf(usuario.getCpf())
                .setEmail(usuario.getEmail())
                .setSenha(usuario.getSenha())
                .setTelefone(usuario.getTelefone())
                .setTipoUsuario(TipoUsuario.valueOf(usuario.getTipoUsuario().name()))
                .build();
    }
}
