package com.med.producer;

import com.med.producer.models.NotificationMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaProducerApplication {

    private KafkaTemplate<String, NotificationMessage> kafkaTemplate;

    public  void run(String args) throws Exception {
        log.info("Producer started");
    }

}
