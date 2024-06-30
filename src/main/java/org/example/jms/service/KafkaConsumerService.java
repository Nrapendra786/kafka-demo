package org.example.jms.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.example.jms.enums.TaskStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Iterator;

@Service
@Slf4j
public class KafkaConsumerService {
    @Autowired
    KafkaConsumer<String, TaskStatus> kafkaConsumer;

    public TaskStatus getLatestTaskStatus(String taskId) {

        ConsumerRecord<String, TaskStatus> latestUpdate = null;
        ConsumerRecords<String, TaskStatus> consumerRecords = kafkaConsumer.poll(Duration.ofMillis(1000));
        if (!consumerRecords.isEmpty()) {
            Iterator itr = consumerRecords.records(taskId).iterator();
            while (itr.hasNext()) {
                latestUpdate = (ConsumerRecord<String, TaskStatus>) itr.next();
            }
            log.info("Latest updated status : " + latestUpdate.value());
        }
        return latestUpdate != null ? latestUpdate.value() : null;
    }
}


