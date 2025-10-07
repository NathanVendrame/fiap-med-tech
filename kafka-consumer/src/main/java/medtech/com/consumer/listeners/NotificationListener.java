package medtech.com.consumer.listeners;

import lombok.RequiredArgsConstructor;
import medtech.com.consumer.models.NotificationMessage;
import medtech.com.consumer.services.WhatsAppNotification;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class NotificationListener {
    private static final Logger log = LoggerFactory.getLogger(NotificationListener.class);

    private final WhatsAppNotification whatsAppNotification;

    @KafkaListener(topics = "notification-new-schedule", groupId = "notification-consumer")
    public void onMessage(ConsumerRecord<String, NotificationMessage> record) {
        NotificationMessage payload = record.value();
        log.info("Mensagem recebida | topic={}, partition={}, offset={}, ts={}, key={}, headers={}, payload={}",
                record.topic(), record.partition(), record.offset(), record.timestamp(), record.key(), record.headers(), payload);
        whatsAppNotification.sendAppointmentNotification(payload);
    }
}
