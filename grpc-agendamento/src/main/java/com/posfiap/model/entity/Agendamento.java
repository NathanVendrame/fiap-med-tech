package com.posfiap.model.entity;

import com.posfiap.model.enums.AgendamentoStatusEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long pacienteId;

    private Long medicoId;

    private LocalDate data;

    private AgendamentoStatusEnum agendamentoStatus;

    public Agendamento() {}

    public Agendamento(Long pacienteId, Long medicoId, LocalDate data, AgendamentoStatusEnum agendamentoStatus) {
        this.pacienteId = pacienteId;
        this.medicoId = medicoId;
        this.data = data;
        this.agendamentoStatus = agendamentoStatus;
    }
}
