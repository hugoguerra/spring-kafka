package test.kafka.demo.project.main.constants;

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
