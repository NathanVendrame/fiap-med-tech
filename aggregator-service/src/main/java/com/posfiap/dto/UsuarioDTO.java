package com.posfiap.dto;

import com.posfiap.usuario.UsuarioProto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.graphql.data.method.annotation.SchemaMapping;

@Getter
@Setter
@SchemaMapping("Usuario")
public class UsuarioDTO {

    private Long usuarioId;

    private String nome;

    private String cpf;

    private String email;

    private String senha;

    private String telefone;

    private String tipoUsuario;

    public UsuarioDTO() {}

    public UsuarioDTO(UsuarioProto usuario) {
        this.usuarioId = usuario.getUsuarioId();
        this.nome = usuario.getNome();
        this.cpf = usuario.getCpf();
        this.email = usuario.getEmail();
        this.senha = usuario.getSenha();
        this.telefone = usuario.getTelefone();
        this.tipoUsuario = usuario.getTipoUsuario().name();
    }
}
