package com.posfiap.repository;

import com.posfiap.model.entity.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    List<Agendamento> findByPacienteId(Long pacienteId);
    List<Agendamento> findByMedicoId(Long medicoId);
}
