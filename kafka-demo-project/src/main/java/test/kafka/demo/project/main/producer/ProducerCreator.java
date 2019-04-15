package test.kafka.demo.project.main.producer;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.LongSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import test.kafka.demo.project.main.constants.IKafkaConstants;
import test.kafka.demo.project.main.dto.ClientDTO;
import test.kafka.demo.project.main.Runner;
import test.kafka.demo.project.mapper.serializer.CustomSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class ProducerCreator implements Runner {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProducerCreator.class);

    private Producer<Long, ClientDTO> createProducer() {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, IKafkaConstants.KAFKA_BROKERS);
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, IKafkaConstants.CLIENT_ID);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, CustomSerializer.class.getName());

        return new KafkaProducer<Long, ClientDTO>(properties);
    }

    @Override
    public void run() {
        Producer<Long, ClientDTO> producer = createProducer();

        int index = 0;
        do {
            ClientDTO client = serializerClient(index);

            // key: LongSerializer | value: CustomSerializer
            ProducerRecord<Long, ClientDTO> record =
                            new ProducerRecord<Long, ClientDTO>(IKafkaConstants.TOPIC_NAME, client.getId(), client);

            try {
                RecordMetadata metadata = producer.send(record).get();
                LOGGER.debug("Message " + client.toString() + " sent!!!");
                LOGGER.debug("Record sent with key " + index + " to partition "
                                + metadata.partition() + " with offset " + metadata.offset());
            } catch (ExecutionException ee) {
                LOGGER.error("Error in sending record - 1", ee);
            } catch (InterruptedException ie) {
                LOGGER.error("Error in sending record - 2", ie);
            } catch (Exception e) {
                LOGGER.error("Error in sending record - 3", e);
            }

            index++;
        } while (index < IKafkaConstants.MESSAGE_COUNT);
    }

    private ClientDTO serializerClient(int index) {
        return new ClientDTO(Long.valueOf(index), "Hugo" + index, 23 + index);
    }
}