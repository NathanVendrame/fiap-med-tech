package com.posfiap.service.handler;

import com.posfiap.agendamento.*;
import com.posfiap.exception.AgendamentoNaoEncontradoException;
import com.posfiap.model.entity.Agendamento;
import com.posfiap.model.enums.AgendamentoStatusEnum;
import com.posfiap.repository.AgendamentoRepository;
import com.posfiap.utils.Converter;
import com.posfiap.utils.EntityMassageMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class AgendamentoRequestHandler {

    private final AgendamentoRepository agendamentoRepository;

    public CreateAgendamentoResponse createAgendamento(CreateAgendamentoRequest createAgendamentoRequest) {
        Agendamento agendamento = EntityMassageMapper.toEntity(createAgendamentoRequest);
        return EntityMassageMapper.toCreateAgendamentoResponse(
                agendamentoRepository.save(agendamento));
    }

    public GetAgendamentoByIdResponse getAgendamentoById(GetAgendamentoByIdRequest getAgendamentoByIdRequest) {
        Agendamento agendamento = findById(getAgendamentoByIdRequest.getAgendamentoId());

        return EntityMassageMapper.toGetAgendamentoByIdResponse(agendamento);
    }


    public UpdateDataAgendamentoResponse updateDataAgendamento(
            UpdateDataAgendamentoRequest updateDataAgendamentoRequest) {
        Agendamento agendamento = findById(updateDataAgendamentoRequest.getAgendamentoId());
        agendamento.setData(Converter.stringToLocalDate(updateDataAgendamentoRequest.getDataAgendamento()));
        agendamentoRepository.save(agendamento);

        return UpdateDataAgendamentoResponse.newBuilder()
                .setMessage("Data do agendamento alterado para " + updateDataAgendamentoRequest.getDataAgendamento())
                .build();
    }

    public CompleteAgendamentoResponse completeAgendamento(CompleteAgendamentoRequest completeAgendamentoRequest) {
        Agendamento agendamento = findById(completeAgendamentoRequest.getAgendamentoId());
        agendamento.setAgendamentoStatus(AgendamentoStatusEnum.COMPARECEU);
        agendamentoRepository.save(agendamento);

        return CompleteAgendamentoResponse.newBuilder()
                .setMessage("Status do agendamento alterado com sucesso")
                .build();
    }

    public CancelAgendamentoResponse cancelAgendamento(CancelAgendamentoRequest cancelAgendamentoRequest) {
        Agendamento agendamento = findById(cancelAgendamentoRequest.getAgendamentoId());
        agendamento.setAgendamentoStatus(AgendamentoStatusEnum.CANCELADO);
        agendamentoRepository.save(agendamento);

        return CancelAgendamentoResponse.newBuilder()
                .setMessage("Agendamento cancelado com sucesso")
                .build();
    }

    public GetListAgendamentoByPacienteIdResponse getListAgendamentoByPacienteId(
            GetListAgendamentoByPacienteIdRequest getListAgendamentoByPacienteIdRequest) {
        List<Agendamento> agendamentos = agendamentoRepository.findByPacienteId(
                getListAgendamentoByPacienteIdRequest.getPacienteId());

        return EntityMassageMapper.toGetListAgendamentoByPacienteIdResponse(agendamentos);
    }

    public GetListAgendamentoByMedicoIdResponse getListAgendamentoByMedicoId(
            GetListAgendamentoByMedicoIdRequest getListAgendamentoByPacienteIdRequest) {
        List<Agendamento> agendamentos = agendamentoRepository.findByMedicoId(
                getListAgendamentoByPacienteIdRequest.getMedicoId());

        return EntityMassageMapper.toGetListAgendamentoByMedicoIdResponse(agendamentos);
    }

    private Agendamento findById(Long id) {
        return agendamentoRepository.findById(id)
                .orElseThrow(() -> new AgendamentoNaoEncontradoException(id));
    }
}
