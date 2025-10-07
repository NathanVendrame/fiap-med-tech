// src/main/java/com/med/producer/config/KafkaConfig.java
package com.med.producer.config;

import com.med.producer.models.NotificationMessage;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.util.StringUtils;

@Configuration
public class KafkaConfig {

    @Bean
    public ProducerFactory<String, NotificationMessage> producerFactory(Environment env) {
        String bs = env.getProperty("spring.kafka.bootstrap-servers");
        if (!StringUtils.hasText(bs)) {
            bs = "localhost:29092"; // fallback no host (macOS)
        }

        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bs);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        props.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);

        return new DefaultKafkaProducerFactory<>(props);
    }

    @Bean
    public KafkaTemplate<String, NotificationMessage> kafkaTemplate(ProducerFactory<String, NotificationMessage> pf) {
        return new KafkaTemplate<>(pf);
    }
}
