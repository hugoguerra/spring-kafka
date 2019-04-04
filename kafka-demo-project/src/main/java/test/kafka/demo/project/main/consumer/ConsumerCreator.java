package test.kafka.demo.project.main.consumer;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import test.kafka.demo.project.constants.IKafkaConstants;
import test.kafka.demo.project.dto.ClientDTO;
import test.kafka.demo.project.main.Runner;
import test.kafka.demo.project.mapper.deserializer.CustomDeserializer;

import java.util.Collections;
import java.util.Properties;

public class ConsumerCreator implements Runner {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerCreator.class);

    private Consumer<Long, ClientDTO> createConsumer() {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, IKafkaConstants.KAFKA_BROKERS);
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, IKafkaConstants.GROUP_ID_CONFIG);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, CustomDeserializer.class.getName());
        properties.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, IKafkaConstants.MAX_POLL_RECORDS);
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, IKafkaConstants.OFFSET_RESET_EARLIER);

        Consumer<Long, ClientDTO> consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Collections.singletonList(IKafkaConstants.TOPIC_NAME));
        return consumer;
    }

    @Override
    public void run() {
        Consumer<Long, ClientDTO> consumer = createConsumer();

        while (true) {
            ConsumerRecords<Long, ClientDTO> messages = consumer.poll(100);
            if (messages.isEmpty()) {
                LOGGER.debug("No messages found for topic " + IKafkaConstants.TOPIC_NAME);
                break;
            }

            for (ConsumerRecord<Long, ClientDTO> message : messages) {
                System.out.println("Message received " + message.value().toString());
            }
        }
    }
}
