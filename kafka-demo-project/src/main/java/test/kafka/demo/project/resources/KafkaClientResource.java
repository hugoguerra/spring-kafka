package test.kafka.demo.project.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.kafka.demo.project.dto.ClientDTO;
import test.kafka.demo.project.service.KafkaClientService;

@RestController
@RequestMapping("/kafka/message")
public class KafkaClientResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaClientResource.class);

    @Autowired
    private KafkaClientService service;

    @PostMapping(value = "/producer",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Boolean> sendMessage(@RequestBody ClientDTO client) {
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.producer(client));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
