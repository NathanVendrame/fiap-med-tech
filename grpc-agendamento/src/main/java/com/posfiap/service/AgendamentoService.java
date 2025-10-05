package com.posfiap.service;

import com.posfiap.agendamento.*;
import com.posfiap.service.handler.AgendamentoRequestHandler;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@AllArgsConstructor
@GrpcService
public class AgendamentoService extends AgendamentoServiceGrpc.AgendamentoServiceImplBase {

    private final AgendamentoRequestHandler agendamentoRequestHandler;

    @Override
    public void createAgendamento(CreateAgendamentoRequest request, StreamObserver<CreateAgendamentoResponse> responseObserver) {
        responseObserver.onNext(agendamentoRequestHandler.createAgendamento(request));
        responseObserver.onCompleted();
    }

    @Override
    public void updateDataAgendamento(UpdateDataAgendamentoRequest request, StreamObserver<UpdateDataAgendamentoResponse> responseObserver) {
        responseObserver.onNext(agendamentoRequestHandler.updateDataAgendamento(request));
        responseObserver.onCompleted();
    }

    @Override
    public void getAgendamentoById(GetAgendamentoByIdRequest request, StreamObserver<GetAgendamentoByIdResponse> responseObserver) {
        responseObserver.onNext(agendamentoRequestHandler.getAgendamentoById(request));
        responseObserver.onCompleted();
    }

    @Override
    public void completeAgendamento(CompleteAgendamentoRequest request, StreamObserver<CompleteAgendamentoResponse> responseObserver) {
        responseObserver.onNext(agendamentoRequestHandler.completeAgendamento(request));
        responseObserver.onCompleted();
    }

    @Override
    public void cancelAgendamento(CancelAgendamentoRequest request, StreamObserver<CancelAgendamentoResponse> responseObserver) {
        responseObserver.onNext(agendamentoRequestHandler.cancelAgendamento(request));
        responseObserver.onCompleted();
    }

    @Override
    public void getListAgendamentoByPacienteId(GetListAgendamentoByPacienteIdRequest request, StreamObserver<GetListAgendamentoByPacienteIdResponse> responseObserver) {
        responseObserver.onNext(agendamentoRequestHandler.getListAgendamentoByPacienteId(request));
        responseObserver.onCompleted();
    }

    @Override
    public void getListAgendamentoByMedicoId(GetListAgendamentoByMedicoIdRequest request, StreamObserver<GetListAgendamentoByMedicoIdResponse> responseObserver) {
        responseObserver.onNext(agendamentoRequestHandler.getListAgendamentoByMedicoId(request));
        responseObserver.onCompleted();
    }
}
