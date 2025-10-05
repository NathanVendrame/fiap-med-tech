package com.posfiap.service;

import com.posfiap.agendamento.*;
import com.posfiap.dto.*;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendamentoService {

    @GrpcClient("grpc-agendamento")
    private AgendamentoServiceGrpc.AgendamentoServiceBlockingStub agendamentoServiceBlockingStub;

    public CreateAgendamentoResponseDTO createAgendamento(AgendamentoDTO agendamentoDTO) {
        CreateAgendamentoRequest request = CreateAgendamentoRequest.newBuilder()
                .setPacienteId(agendamentoDTO.getPacienteId())
                .setMedicoId(agendamentoDTO.getMedicoId())
                .setDataAgendamento(agendamentoDTO.getDataAgendamento())
                .build();
        CreateAgendamentoResponse response = agendamentoServiceBlockingStub.createAgendamento(request);

        return new CreateAgendamentoResponseDTO(response.getAgendamentoId(), response.getMessage());
    }

    public UpdateDataAgendamentoResponseDTO updateDataAgendamento(AgendamentoDTO agendamentoDTO) {
        UpdateDataAgendamentoRequest request = UpdateDataAgendamentoRequest.newBuilder()
                .setAgendamentoId(agendamentoDTO.getAgendamentoId())
                .setDataAgendamento(agendamentoDTO.getDataAgendamento())
                .build();
        UpdateDataAgendamentoResponse response =  agendamentoServiceBlockingStub.updateDataAgendamento(request);

        return new UpdateDataAgendamentoResponseDTO(response.getMessage());
    }

    public CompleteAgendamentoResponseDTO completeAgendamento(Long agendamentoId) {
        CompleteAgendamentoRequest request = CompleteAgendamentoRequest.newBuilder()
                .setAgendamentoId(agendamentoId)
                .build();
        CompleteAgendamentoResponse response =  agendamentoServiceBlockingStub.completeAgendamento(request);

        return new CompleteAgendamentoResponseDTO(response.getMessage());
    }

    public CancelAgendamentoResponseDTO cancelAgendamento(Long agendamentoId) {
        CancelAgendamentoRequest request = CancelAgendamentoRequest.newBuilder()
                .setAgendamentoId(agendamentoId)
                .build();
        CancelAgendamentoResponse response =  agendamentoServiceBlockingStub.cancelAgendamento(request);

        return new CancelAgendamentoResponseDTO(response.getMessage());
    }

    public AgendamentoDTO getAgendamentoById(Long agendamentoId) {
        GetAgendamentoByIdRequest request = GetAgendamentoByIdRequest.newBuilder()
                .setAgendamentoId(agendamentoId)
                .build();
        GetAgendamentoByIdResponse response = agendamentoServiceBlockingStub.getAgendamentoById(request);

        return new AgendamentoDTO(response.getAgendamento());
    }

    public List<AgendamentoDTO> getListAgendamentoByPacienteId(Long pacienteId) {
        GetListAgendamentoByPacienteIdRequest request = GetListAgendamentoByPacienteIdRequest.newBuilder()
                .setPacienteId(pacienteId)
                .build();
        GetListAgendamentoByPacienteIdResponse response = agendamentoServiceBlockingStub
                .getListAgendamentoByPacienteId(request);

        return response.getAgendamentoList().stream().map(AgendamentoDTO::new).toList();
    }

    public List<AgendamentoDTO> getListAgendamentoByMedicoId(Long medicoId) {
        GetListAgendamentoByMedicoIdRequest request = GetListAgendamentoByMedicoIdRequest.newBuilder()
                .setMedicoId(medicoId)
                .build();
        GetListAgendamentoByMedicoIdResponse response = agendamentoServiceBlockingStub
                .getListAgendamentoByMedicoId(request);

        return response.getAgendamentoList().stream().map(AgendamentoDTO::new).toList();
    }


}
