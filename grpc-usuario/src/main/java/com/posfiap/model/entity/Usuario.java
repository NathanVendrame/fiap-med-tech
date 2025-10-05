package com.posfiap.model.entity;

import com.posfiap.model.enums.TipoUsuarioEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String cpf;

    private String email;

    private String senha;

    private String telefone;

    private TipoUsuarioEnum tipoUsuario;

    public Usuario() {}

    public Usuario(String nome, String cpf, String email, String senha, String telefone, TipoUsuarioEnum tipoUsuario) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.tipoUsuario = tipoUsuario;
    }
}
