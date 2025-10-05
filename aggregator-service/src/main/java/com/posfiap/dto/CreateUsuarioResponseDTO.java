package com.posfiap.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.graphql.data.method.annotation.SchemaMapping;

@Getter
@Setter
@SchemaMapping("CreateUsuarioResponse")
public class CreateUsuarioResponseDTO {

    private Long usuarioId;

    private String message;

    public CreateUsuarioResponseDTO() { }

    public CreateUsuarioResponseDTO(Long usuarioId, String message) {
        this.usuarioId = usuarioId;
        this.message = message;
    }
}
