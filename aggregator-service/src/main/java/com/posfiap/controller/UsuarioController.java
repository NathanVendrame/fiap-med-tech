package com.posfiap.controller;

import com.posfiap.dto.CreateUsuarioResponseDTO;
import com.posfiap.dto.UsuarioDTO;
import com.posfiap.service.UsuarioService;
import com.posfiap.usuario.*;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@AllArgsConstructor
@Controller
public class UsuarioController {

    private final UsuarioService usuarioService;

    @QueryMapping
    public UsuarioDTO getUsuarioById(@Argument Long id) {
        return usuarioService.getUsuarioById(id);
    }

    @PreAuthorize("hasAnyRole('MEDICO','ENFERMEIRO')")
    @QueryMapping
    public List<UsuarioDTO> listUsuario() {
        return usuarioService.listUsuario();
    }

    @MutationMapping
    public CreateUsuarioResponseDTO createUsuario(@Argument UsuarioDTO usuario) {
        return usuarioService.createUsuario(usuario);
    }
}
