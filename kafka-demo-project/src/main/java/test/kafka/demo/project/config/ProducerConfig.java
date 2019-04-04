package test.kafka.demo.project.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import test.kafka.demo.project.dto.ClientDTO;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ProducerConfig {

    @Value("${spring.kafka.producer.bootstrap-servers}")
    private String brokers;

    @Value("${spring.kafka.producer.client-id}")
    private String clientConfig;

    @Value("${spring.kafka.producer.key-deserializer}")
    private String keySerializer;

    @Value("${spring.kafka.producer.value-deserializer}")
    private String valueSerializer;

    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(org.apache.kafka.clients.producer.ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokers);
        properties.put(org.apache.kafka.clients.producer.ProducerConfig.CLIENT_ID_CONFIG, clientConfig);
        properties.put(org.apache.kafka.clients.producer.ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, keySerializer);
        properties.put(org.apache.kafka.clients.producer.ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, valueSerializer);

        return properties;
    }

    @Bean
    public ProducerFactory<Long, ClientDTO> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<Long, ClientDTO> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
