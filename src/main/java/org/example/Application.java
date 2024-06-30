package org.example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.kafka.core.KafkaTemplate;
import org.example.jms.enums.TaskStatus;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    KafkaTemplate kafkaTemplate;

    @Autowired
    KafkaProperties kafkaProperties;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        kafkaTemplate.send("general-task-topic", "taskId", new TaskStatus("taskId", "taskName", 50.0f, TaskStatus.Status.RUNNING));
        kafkaTemplate.send("general-task-topic", "taskId", new TaskStatus("taskId", "taskName", 100.0f, TaskStatus.Status.FINISHED));
        kafkaTemplate.send("general-task-topic","test-message");
    }
}