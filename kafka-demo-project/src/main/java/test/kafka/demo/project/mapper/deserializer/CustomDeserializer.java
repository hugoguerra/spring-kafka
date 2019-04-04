package test.kafka.demo.project.mapper.deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import test.kafka.demo.project.dto.ClientDTO;

import java.util.Map;

public class CustomDeserializer implements org.apache.kafka.common.serialization.Deserializer<ClientDTO> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomDeserializer.class);

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public ClientDTO deserialize(String topic, byte[] data) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(data, ClientDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("There was an error deserializing clientDTO");
            return null;
        }
    }

    @Override
    public void close() {

    }
}
