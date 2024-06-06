package com.example.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produce")
public class ProducerController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping("/{message}")
    public String sendMessage(@PathVariable String message) {
        kafkaTemplate.send("your_topic_name", message);
        return "Message sent to Kafka: " + message;
    }
}
