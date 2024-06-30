package org.example.jms.service;

import lombok.extern.slf4j.Slf4j;
import org.example.jms.enums.TaskStatus;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class kafKaTopicListeners {

    @KafkaListener(topics = {"general-task-topic"}, groupId = "task-group")
    public void consume(TaskStatus taskStatus) {
        log.info(String.format("Task status is updated : " + taskStatus));
    }
}

