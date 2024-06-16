package com.example.consumer

import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class KafkaListenerService (
    @Value("\${twilio.accountSid}")
    private val accountSid: String,
    @Value("\${twilio.authToken}")
    private val authToken: String,
    @Value("\${twilio.numberFrom}")
    private val numberFrom: String,
    @Value("\${twilio.numberTo}")
    private val numberTo: String,
    private val smsSenderService: SmsSenderService
){

    @KafkaListener(topics = ["topic-name"], groupId = "group_id")
    fun listen(message: String) {
        println("Received message: $message")
        smsSenderService.sendSmsNotification(accountSid, authToken, numberFrom, numberTo, message)
    }

}
