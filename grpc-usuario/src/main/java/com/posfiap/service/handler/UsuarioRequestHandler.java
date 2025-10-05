package com.posfiap.service.handler;

import com.posfiap.model.entity.Usuario;
import com.posfiap.exception.UsuarioNaoEncontradoException;
import com.posfiap.repository.UsuarioRepository;
import com.posfiap.usuario.*;
import com.posfiap.utils.EntityMessageMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UsuarioRequestHandler {

    private final UsuarioRepository usuarioRepository;

    public GetUsuarioByIdResponse getUsuarioById(GetUsuarioByIdRequest getUsuarioByIdRequest) {
        Usuario usuario = usuarioRepository.findById(getUsuarioByIdRequest.getUsuarioId())
                .orElseThrow(() ->
                        new UsuarioNaoEncontradoException(getUsuarioByIdRequest.getUsuarioId()));

        return EntityMessageMapper.toGetUsuarioByIdResponse(usuario);
    }

    public ListUsuarioResponse getAllUsuarios() {
        return EntityMessageMapper.toListUsuarioResponse(
                usuarioRepository.findAll()
        );
    }

    public CreateUsuarioResponse createUsuario(CreateUsuarioRequest createUsuarioRequest) {
        Usuario usuario = EntityMessageMapper.toEntity(createUsuarioRequest);
        return EntityMessageMapper.toCreateUsuarioResponse(
                usuarioRepository.save(usuario));
    }
}
