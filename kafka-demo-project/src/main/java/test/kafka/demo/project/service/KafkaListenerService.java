package test.kafka.demo.project.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import test.kafka.demo.project.dto.ClientDTO;

@Component
public class KafkaListenerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaListenerService.class);

    private static final String TOPIC_NAME_BOOT = "client-topic-spring-boot";

    @KafkaListener(topics = TOPIC_NAME_BOOT)
    public boolean consumer(ClientDTO clientDTO) {
        try {
            LOGGER.info("Start the consumer");
            LOGGER.info("Consumer Message -> {}", clientDTO.toString());
            return true;
        } catch (Exception e) {
            LOGGER.error("There was an error - consumer message", e);
            return false;
        }
    }
}
