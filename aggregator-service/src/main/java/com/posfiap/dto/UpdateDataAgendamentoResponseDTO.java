package com.posfiap.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.graphql.data.method.annotation.SchemaMapping;

@Getter
@Setter
@SchemaMapping("UpdateDataAgendamentoResponse")
public class UpdateDataAgendamentoResponseDTO {

    private String message;

    public UpdateDataAgendamentoResponseDTO(String message) {
        this.message = message;
    }
}
