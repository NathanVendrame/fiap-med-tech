package com.posfiap.service.handler;

import com.posfiap.model.entity.Usuario;
import com.posfiap.exception.UsuarioNaoEncontradoException;
import com.posfiap.model.enums.TipoUsuarioEnum;
import com.posfiap.repository.UsuarioRepository;
import com.posfiap.usuario.*;
import com.posfiap.utils.EntityMessageMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UsuarioRequestHandler {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

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

    public CreateUsuarioResponse createUsuario(CreateUsuarioRequest req) {
        if (usuarioRepository.findByEmail(req.getEmail()).isPresent()) {
            return CreateUsuarioResponse.newBuilder()
                    .setUsuarioId(0)
                    .setMessage("Email já cadastrado.")
                    .build();
        }

        if (usuarioRepository.findByCpf(req.getCpf()).isPresent()) {
            return CreateUsuarioResponse.newBuilder()
                    .setUsuarioId(0)
                    .setMessage("CPF já cadastrado.")
                    .build();
        }

        var usuario = new Usuario();
        usuario.setNome(req.getNome());
        usuario.setCpf(req.getCpf());
        usuario.setEmail(req.getEmail());
        usuario.setTelefone(req.getTelefone());
        usuario.setTipoUsuario(TipoUsuarioEnum.valueOf(req.getTipoUsuario().name()));
        usuario.setSenha(passwordEncoder.encode(req.getSenha()));

        usuarioRepository.save(usuario);

        return CreateUsuarioResponse.newBuilder()
                .setUsuarioId(usuario.getId())
                .setMessage("Usuário " + usuario.getNome() + " criado com sucesso")
                .build();
    }
}
