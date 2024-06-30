package org.example;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.UUID;

@Component
public class Producer {

    private final KafkaTemplate<Integer, BankTransfer> kafkaTemplate;

    public Producer(KafkaTemplate<Integer,BankTransfer> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Scheduled(fixedDelay = 1000)
    public void send(){
        BankTransfer bankTransfer = new BankTransfer(UUID.randomUUID().toString(),
                UUID.randomUUID().toString(), new Random().nextLong());

        kafkaTemplate.send("bank-transfer", bankTransfer);
    }
}
