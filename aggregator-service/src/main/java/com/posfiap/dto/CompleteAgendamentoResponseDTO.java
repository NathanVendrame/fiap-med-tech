package com.posfiap.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.graphql.data.method.annotation.SchemaMapping;

@Getter
@Setter
@SchemaMapping("CompleteAgendamentoResponse")
public class CompleteAgendamentoResponseDTO {

    private String message;

    public CompleteAgendamentoResponseDTO(String message) {
        this.message = message;
    }
}
