package com.posfiap.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.graphql.data.method.annotation.SchemaMapping;

@Getter
@Setter
@SchemaMapping("CreateAgendamentoResponse")
public class CreateAgendamentoResponseDTO {

    private Long agendamentoId;

    private String message;

    public CreateAgendamentoResponseDTO() { }

    public CreateAgendamentoResponseDTO(Long agendamentoId, String message) {
        this.agendamentoId = agendamentoId;
        this.message = message;
    }
}
