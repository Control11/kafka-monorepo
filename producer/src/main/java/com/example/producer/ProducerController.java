package com.example.producer;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/produce")
public class ProducerController {

    @NonNull
    private final KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping("/{message}")
    public String sendMessage(@PathVariable String message) {
        kafkaTemplate.send("topic-name", message);
        return "Message sent to Kafka: " + message;
    }

}
