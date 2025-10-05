package com.posfiap.dto;

import com.posfiap.agendamento.AgendamentoProto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.graphql.data.method.annotation.SchemaMapping;

@Getter
@Setter
@SchemaMapping("Agendamento")
public class AgendamentoDTO {

    private Long agendamentoId;

    private Long pacienteId;

    private Long medicoId;

    private String dataAgendamento;

    private String agendamentoStatus;

    public AgendamentoDTO() {}

    public AgendamentoDTO(AgendamentoProto agendamentoProto) {
        this.agendamentoId = agendamentoProto.getAgendamentoId();
        this.pacienteId = agendamentoProto.getPacienteId();
        this.medicoId = agendamentoProto.getMedicoId();
        this.dataAgendamento = agendamentoProto.getDataAgendamento();
        this.agendamentoStatus = agendamentoProto.getAgendamentoStatus().name();
    }
}
