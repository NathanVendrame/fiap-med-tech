package com.posfiap.controller;

import com.posfiap.dto.*;
import com.posfiap.service.AgendamentoService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@AllArgsConstructor
@Controller
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    @MutationMapping
    public CreateAgendamentoResponseDTO createAgendamento(@Argument AgendamentoDTO agendamento) {
        return agendamentoService.createAgendamento(agendamento);
    }

    @MutationMapping
    public UpdateDataAgendamentoResponseDTO updateDataAgendamento(@Argument AgendamentoDTO dataAgendamento) {
        return agendamentoService.updateDataAgendamento(dataAgendamento);
    }

    @MutationMapping
    public CompleteAgendamentoResponseDTO completeAgendamento(@Argument Long agendamentoId) {
        return agendamentoService.completeAgendamento(agendamentoId);
    }

    @MutationMapping
    public CancelAgendamentoResponseDTO cancelAgendamento(@Argument Long agendamentoId) {
        return agendamentoService.cancelAgendamento(agendamentoId);
    }

    @QueryMapping
    public AgendamentoDTO getAgendamentoById(@Argument Long agendamentoId) {
        return agendamentoService.getAgendamentoById(agendamentoId);
    }

    @QueryMapping
    public List<AgendamentoDTO> getListAgendamentoByPacienteId(@Argument Long pacienteId) {
        return agendamentoService.getListAgendamentoByPacienteId(pacienteId);
    }

    @QueryMapping
    public List<AgendamentoDTO> getListAgendamentoByMedicoId(@Argument Long medicoId) {
        return agendamentoService.getListAgendamentoByMedicoId(medicoId);
    }
}
