package com.posfiap.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.graphql.data.method.annotation.SchemaMapping;

@Getter
@Setter
@SchemaMapping("CancelAgendamentoResponse")
public class CancelAgendamentoResponseDTO {

    private String message;

    public CancelAgendamentoResponseDTO(String message) {
        this.message = message;
    }
}
