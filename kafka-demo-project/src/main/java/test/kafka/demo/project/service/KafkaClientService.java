package test.kafka.demo.project.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import test.kafka.demo.project.ClientAvro;
import test.kafka.demo.project.main.dto.ClientDTO;

@Component
public class KafkaClientService {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaClientService.class);

    private static final String TOPIC_NAME_BOOT = "client-topic-spring-boot";

    @Autowired
        private KafkaTemplate<Long, ClientAvro> kafkaTemplate;

    public boolean producer(ClientAvro client) {
        try {
            LOGGER.info("Start the producer");
            LOGGER.info("Producer Message -> {}", client.toString());
            ListenableFuture<SendResult<Long, ClientAvro>> send = this.kafkaTemplate.send(TOPIC_NAME_BOOT, client);

            return true;
        } catch (Exception e) {
            LOGGER.error("There was an error - producer message", e);
            return false;
        }

    }
}
