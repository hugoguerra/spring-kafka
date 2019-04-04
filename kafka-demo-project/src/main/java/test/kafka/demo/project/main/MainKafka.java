package test.kafka.demo.project.main;

import test.kafka.demo.project.main.consumer.ConsumerCreator;
import test.kafka.demo.project.main.producer.ProducerCreator;

public class MainKafka {

    public static void main(String[] args) {
        ProducerCreator producer = new ProducerCreator();
        producer.run();

        ConsumerCreator consumer = new ConsumerCreator();
        consumer.run();
    }
}
