package com.example.demo.kafka;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.json.simple.JSONObject;
import org.springframework.boot.json.JsonParser;

import java.util.Collections;
import java.util.Properties;

public class KafkaConsumer {
    private final String bootstrapServers;
    private final String groupId;
    private final String topicName;

    public KafkaConsumer(String bootstrapServers, String groupId, String topicName) {
        this.bootstrapServers = bootstrapServers;
        this.groupId = groupId;
        this.topicName = topicName;
    }

    public void consume() {
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        KafkaConsumer kafkaConsumer = new KafkaConsumer("localhost:9092", "bet_detail_group", "bet_detail");
        
        kafkaConsumer.consume();
        
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);

        consumer.subscribe(Collections.singletonList(topicName));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                String message = record.value();
                // Parse message JSON to get returns amount
                JSONObject jsonObject = JsonParser.parseString(message).getAsJsonObject();
                double returnsAmount = jsonObject.get("returns").getAsDouble();
                if (returnsAmount >= 1500.00) {
                    System.out.println("Notification: Returns amount of " + returnsAmount + " exceeds 1500.00");
                }
            }
        }


    }
}
