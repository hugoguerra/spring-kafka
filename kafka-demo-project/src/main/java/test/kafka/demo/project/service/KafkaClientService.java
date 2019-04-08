package test.kafka.demo.project.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import test.kafka.demo.project.dto.ClientDTO;

@Component
public class KafkaClientService {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaClientService.class);

    private static final String TOPIC_NAME_BOOT = "client-topic-spring-boot";

    @Autowired
    private KafkaTemplate<Long,ClientDTO> kafkaTemplate;

    public boolean producer(ClientDTO client) {
        try {
            LOGGER.info("Start the producer");
            LOGGER.info("Producer Message -> {}", client.toString());
            this.kafkaTemplate.send(TOPIC_NAME_BOOT, client);
            return true;
        } catch (Exception e) {
            LOGGER.error("There was an error - producer message", e);
            return false;
        }

    }
}
