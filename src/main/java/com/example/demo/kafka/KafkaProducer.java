package com.example.demo.kafka;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import java.util.Properties;

public class KafkaProducer {
    private final String bootstrapServers;
    private final String topicName;

    public KafkaProducer(String bootstrapServers, String topicName) {
        this.bootstrapServers = bootstrapServers;
        this.topicName = topicName;
    }

    public void produce(String message) {
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);

        ProducerRecord<String, String> record = new ProducerRecord<>(topicName, message);
        producer.send(record, new Callback() {
            @Override
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                if (exception != null) {
                    exception.printStackTrace();
                } else {
                    System.out.println("Message sent to partition " + metadata.partition() + " with offset " + metadata.offset());
                }
            }
        });
        producer.flush();
        producer.close();
        KafkaProducer kafkaProducer = new KafkaProducer("localhost:9092", "bet_detail");
        kafkaProducer.produce("New bet record inserted.");

    }
}

