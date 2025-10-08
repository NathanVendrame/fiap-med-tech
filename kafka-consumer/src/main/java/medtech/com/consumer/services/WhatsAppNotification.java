package medtech.com.consumer.services;

import lombok.extern.slf4j.Slf4j;
import medtech.com.consumer.models.NotificationMessage;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class WhatsAppNotification {

    public void sendAppointmentNotification(NotificationMessage notificationMessage) {

        log.info("Consulta marcada. Enviando notificação por whatsApp ao cliente.");
        log.info("Simulando envio de notificação pela API do WhatsApp...");
        log.info("Enviando msg para telefone do cliente: {}", notificationMessage.getPacienteTelefone());
        log.info("Olá, {}. Sua consulta está agendada para a data {} com Dr {}."
                ,notificationMessage.getPacienteNome(), notificationMessage.getData(), notificationMessage.getMedicoNome());

    }

}
