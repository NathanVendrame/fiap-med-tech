package com.posfiap.service;

import com.posfiap.agendamento.*;
import com.posfiap.dto.*;
import com.posfiap.exception.UsuarioInformadoNaoMedicoException;
import com.posfiap.exception.UsuarioInformadoNaoPacienteException;
import com.posfiap.usuario.GetUsuarioByIdRequest;
import com.posfiap.usuario.GetUsuarioByIdResponse;
import com.posfiap.usuario.TipoUsuario;
import com.posfiap.usuario.UsuarioServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendamentoService {

    @GrpcClient("grpc-agendamento")
    private AgendamentoServiceGrpc.AgendamentoServiceBlockingStub agendamentoServiceBlockingStub;

    @GrpcClient("grpc-usuario")
    private UsuarioServiceGrpc.UsuarioServiceBlockingStub usuarioServiceBlockingStub;

    public CreateAgendamentoResponseDTO createAgendamento(AgendamentoDTO agendamentoDTO) {
        UsuarioDTO paciente = getUsuarioById(agendamentoDTO.getPacienteId());
        if (!paciente.getTipoUsuario().equals(TipoUsuario.PACIENTE.name())) {
            throw new UsuarioInformadoNaoPacienteException(agendamentoDTO.getPacienteId());
        }

        UsuarioDTO medico = getUsuarioById(agendamentoDTO.getMedicoId());
        if (!medico.getTipoUsuario().equals(TipoUsuario.MEDICO.name())) {
            throw new UsuarioInformadoNaoMedicoException(agendamentoDTO.getPacienteId());
        }

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

    private UsuarioDTO getUsuarioById(Long usuarioId) {
        GetUsuarioByIdRequest request = GetUsuarioByIdRequest.newBuilder()
                .setUsuarioId(usuarioId)
                .build();
        GetUsuarioByIdResponse response = usuarioServiceBlockingStub.getUsuarioById(request);

        return new UsuarioDTO(response.getUsuario());
    }

}
