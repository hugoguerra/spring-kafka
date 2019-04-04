## KAFKA
This repository has two kafka examples using custom serializer and deserializer for test.kafka.demo.project.dto.ClientDTO object. The second one in Spring Boot 2.

###### Dependencies
plugins {
	id 'org.springframework.boot' version '2.1.3.RELEASE'
	id 'java'
}

dependencies {
	implementation 'org.springframework.boot:spring-boot-starter-web'
	implementation 'org.springframework.kafka:spring-kafka'
	testImplementation 'org.springframework.boot:spring-boot-starter-test'
	testImplementation 'org.springframework.kafka:spring-kafka-test'
}

###### Configuration
In these two example will be use the same serializer configuration, just in different ways.

> For the main example:
```
public class IKafkaConstants {

    public static String KAFKA_BROKERS = "localhost:9092";
    public static Integer MESSAGE_COUNT=50;
    public static String CLIENT_ID="clientId1";
    public static String TOPIC_NAME="client-topic";
    public static String GROUP_ID_CONFIG="consumerGroup1";
    public static Integer MAX_NO_MESSAGE_FOUND_COUNT=3;
    public static String OFFSET_RESET_LATEST="latest";
    public static String OFFSET_RESET_EARLIER="earliest";
    public static Integer MAX_POLL_RECORDS=3;
}
```
```
public class ProducerCreator ... {
  ...
  Properties properties = new Properties();
  ...
  properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class.getName());
  properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, CustomSerializer.class.getName());
}
```
```
public class ConsumerCreator ... {
  ...
  Properties properties = new Properties();
  ...
  properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class.getName());
  properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, CustomDeserializer.class.getName());
}
```

> For the spring-boot example
```
spring:
  kafka:
    consumer:
      bootstrap-servers: localhost:9092
      group-id: group-id
      auto-offset-reset: earliest
      key-deserializer: org.apache.kafka.common.serialization.LongSerializer
      value-deserializer: test.kafka.demo.project.mapper.deserializer.CustomDeserializer
    producer:
      bootstrap-servers: localhost:9092
      key-deserializer: org.apache.kafka.common.serialization.LongSerializer
      value-deserializer: test.kafka.demo.project.mapper.serializer.CustomSerializer
```

###### KAFKA MAIN EXAMPLE
This example no need to run in spring boot. There a class test.kafka.demo.project.main.MainKafka that execute the part of the Producer and Consumer.

###### KAFKA SPRING BOOT
execute ```./gradlew clean build -x test bootRun``` to start the application.

Two methods to consume:

Producer Resource:
POST -> 

Consumer Resource:
POST -> 


###### KAFKA TOOL
If you want to see the information in kafka, you could install a opensource tool like KafkaTool.






