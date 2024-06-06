package com.example.consumer

import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class KafkaListenerService {

    @KafkaListener(topics = ["your_topic_name"], groupId = "group_id")
    fun listen(message: String) {
        println("Received Message: $message")
    }
}
