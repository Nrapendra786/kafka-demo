package org.example.jms.service;

import lombok.extern.slf4j.Slf4j;
import org.example.jms.enums.TaskStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaProducerService {

    @Autowired
    KafkaTemplate<String, TaskStatus> kafkaTemplate;

    public void send(String topicName, String key, TaskStatus value) {

        var future = kafkaTemplate.send(topicName, key, value);

        future.whenComplete((sendResult, exception) -> {
            if (exception != null) {
                future.completeExceptionally(exception);
            } else {
                future.complete(sendResult);
            }
            log.info("Task status send to Kafka topic : " + value);
        });
    }
}
