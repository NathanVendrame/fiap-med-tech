package com.med.producer.controller;

import com.med.producer.models.NotificationMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/messages")
public class NotificationController {

    private final KafkaTemplate<String, NotificationMessage> kafkaTemplate;

    @PostMapping("/notificationNewSchedule")
    public ResponseEntity<Void> notificationNewSchedule(@RequestBody NotificationMessage notificationMessage) {
        kafkaTemplate.send("notification-new-schedule", notificationMessage);
        return ResponseEntity.ok().build();
    }
}


