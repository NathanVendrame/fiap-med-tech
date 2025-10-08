package com.posfiap.service;

import com.posfiap.dto.NotificationMessageDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationSender {

    private final WebClient notificationWebClient;

    public void enviarNotificacao(String pacienteNome,
                                       String pacienteTelefone,
                                       String medicoNome,
                                       LocalDate data,
                                       String agendamentoStatus) {

        var body = NotificationMessageDTO.builder()
                .pacienteNome(pacienteNome)
                .pacienteTelefone(pacienteTelefone)
                .medicoNome(medicoNome)
                .data(data)
                .agendamentoStatus(agendamentoStatus)
                .build();

        notificationWebClient.post()
                .uri("/messages/notificationNewSchedule")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body)
                .retrieve()
                .toBodilessEntity()
                .doOnSuccess(r -> log.info("Notificação enviada. paciente={}, medico={}", pacienteNome, medicoNome))
                .doOnError(e -> log.error("Falha ao enviar notificação. erro={}", e.getMessage()))
                .subscribe();
    }
}
