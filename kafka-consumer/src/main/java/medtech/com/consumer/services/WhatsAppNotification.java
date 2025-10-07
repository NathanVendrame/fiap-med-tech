package medtech.com.consumer.services;

import lombok.extern.slf4j.Slf4j;
import medtech.com.consumer.models.NotificationMessage;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class WhatsAppNotification {

    public void sendAppointmentNotification(NotificationMessage notificationMessage) {

        log.info("Consulta marcada. Enviando notificação por whatsApp ao cliente.");

        log.info("Simulando envio de notificação pela API do WhatsApp...");

        log.info("Nome do cliente: {}",  notificationMessage.getClientName());
        log.info("Telefone: {}",notificationMessage.getClientPhoneNumber() );
        log.info("Médico responsável: {}", notificationMessage.getDoctorName());
        log.info("{}", notificationMessage.getMessage());
        log.info("Data da consulta: {}", notificationMessage.getAppointmentDate());
        log.info("Horário da consulta: {}", notificationMessage.getAppointmentTime());

    }

}
