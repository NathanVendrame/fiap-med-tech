package com.posfiap.utils;

import com.posfiap.agendamento.*;
import com.posfiap.model.entity.Agendamento;
import com.posfiap.model.enums.AgendamentoStatusEnum;

import java.util.List;

public class EntityMassageMapper {

    public static Agendamento toEntity(CreateAgendamentoRequest createAgendamentoRequest) {
        return new Agendamento(
                createAgendamentoRequest.getPacienteId(),
                createAgendamentoRequest.getMedicoId(),
                Converter.stringToLocalDate(createAgendamentoRequest.getDataAgendamento()),
                AgendamentoStatusEnum.AGENDADO);
    }

    public static CreateAgendamentoResponse toCreateAgendamentoResponse(Agendamento agendamento) {
        return CreateAgendamentoResponse.newBuilder()
                .setAgendamentoId(agendamento.getId())
                .setMessage("Agendamento criado")
                .build();
    }

    public static GetAgendamentoByIdResponse toGetAgendamentoByIdResponse(Agendamento agendamento) {
        return GetAgendamentoByIdResponse.newBuilder()
                .setAgendamento(toAgendamentoProto(agendamento))
                .build();
    }

    public static GetListAgendamentoByPacienteIdResponse toGetListAgendamentoByPacienteIdResponse(List<Agendamento> agendamentos) {
        return GetListAgendamentoByPacienteIdResponse.newBuilder()
                .addAllAgendamento(agendamentos.stream()
                        .map(EntityMassageMapper::toAgendamentoProto)
                        .toList())
                .build();
    }

    public static GetListAgendamentoByMedicoIdResponse toGetListAgendamentoByMedicoIdResponse(List<Agendamento> agendamentos) {
        return GetListAgendamentoByMedicoIdResponse.newBuilder()
                .addAllAgendamento(agendamentos.stream()
                        .map(EntityMassageMapper::toAgendamentoProto)
                        .toList())
                .build();
    }

    private static AgendamentoProto toAgendamentoProto(Agendamento agendamento) {
        return AgendamentoProto.newBuilder()
                .setAgendamentoId(agendamento.getId())
                .setPacienteId(agendamento.getPacienteId())
                .setMedicoId(agendamento.getMedicoId())
                .setDataAgendamento(agendamento.getData().toString())
                .setAgendamentoStatus(AgendamentoStatusProto.valueOf(agendamento.getAgendamentoStatus().name()))
                .build();
    }
}
