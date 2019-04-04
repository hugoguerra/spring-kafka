package test.kafka.demo.project.mapper.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import test.kafka.demo.project.dto.ClientDTO;

import java.util.Map;

public class CustomSerializer implements Serializer<ClientDTO> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomSerializer.class);

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public byte[] serialize(String topic, ClientDTO data) {

        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(data).getBytes();
        } catch (Exception e) {
            LOGGER.error("There was an error serializing clientDTO");
            return null;
        }
    }

    @Override
    public void close() {

    }
}
