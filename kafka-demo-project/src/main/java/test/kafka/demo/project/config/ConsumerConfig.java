package test.kafka.demo.project.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import test.kafka.demo.project.dto.ClientDTO;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ConsumerConfig {

    @Value("${spring.kafka.consumer.bootstrap-servers}")
    private String brokers;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupIds;

    @Value("${spring.kafka.consumer.key-deserializer}")
    private String keySerializer;

    @Value("${spring.kafka.consumer.value-deserializer}")
    private String valueSerializer;

    @Bean
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(org.apache.kafka.clients.consumer.ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, brokers);
        properties.put(org.apache.kafka.clients.consumer.ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, keySerializer);
        properties.put(org.apache.kafka.clients.consumer.ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, valueSerializer);
        properties.put(org.apache.kafka.clients.consumer.ConsumerConfig.GROUP_ID_CONFIG, groupIds);
        properties.put(org.apache.kafka.clients.consumer.ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        return properties;
    }

    @Bean
    @ConditionalOnMissingBean()
    public ConsumerFactory<Long, ClientDTO> consumerFactory() {
        return new DefaultKafkaConsumerFactory(consumerConfigs());
    }

    @Bean
    public KafkaListenerContainerFactory<?> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<Long, ClientDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setBatchListener(true);
        return factory;
    }
}
