package org.example.web;

import org.example.jms.enums.TaskStatus;
import org.example.jms.service.KafkaConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    TaskService taskService;

    @Autowired
    KafkaConsumerService kafkaConsumerService;

    @PostMapping
    public ResponseEntity<TaskResponse> processAsync(@RequestBody TaskRequest taskRequest,
                                                     UriComponentsBuilder b) {
        String taskId = UUID.randomUUID().toString();
        UriComponents progressURL = b.path("/tasks/{id}/progress").buildAndExpand(taskId);
        taskService.process(taskId, taskRequest, b);
        return ResponseEntity.accepted().location(progressURL.toUri()).build();
    }

    @GetMapping("{taskId}/progress")
    public ResponseEntity<?> processAsync(@PathVariable String taskId) {

        TaskStatus taskStatus = kafkaConsumerService.getLatestTaskStatus(taskId);
        if (taskStatus == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(taskStatus);
    }
}
