package com.med.producer.configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ConfigProbe {

    private final Environment env;

    @EventListener(ApplicationReadyEvent.class)
    public void logKafkaBootstrap() {
        String bs = env.getProperty("spring.kafka.bootstrap-servers");
        log.info("spring.kafka.bootstrap-servers='{}'", bs);
    }
}
