package test.kafka.demo.project.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
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
            LOGGER.debug("Producer Message -> " + client.toString());
            kafkaTemplate.send(TOPIC_NAME_BOOT, client);
            return true;
        } catch (Exception e) {
            LOGGER.error("There was an error - producer message", e);
            return false;
        }

    }

    @KafkaListener(topics = TOPIC_NAME_BOOT)
    public boolean consumer(ConsumerRecord<?, ?> consumerRecord) {
        try {
            LOGGER.debug("Producer Message -> " + consumerRecord.toString());
            return true;
        } catch (Exception e) {
            LOGGER.error("There was an error - consumer message", e);
            return false;
        }
    }
}
