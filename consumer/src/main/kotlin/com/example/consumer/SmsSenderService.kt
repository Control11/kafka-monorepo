package com.example.consumer

import com.twilio.Twilio
import com.twilio.rest.api.v2010.account.Message
import com.twilio.type.PhoneNumber
import lombok.AllArgsConstructor
import org.springframework.stereotype.Service

@Service
@AllArgsConstructor
class SmsSenderService {
    fun sendSmsNotification(accountSid: String, authToken: String, numberFrom: String, numberTo: String, message: String) {
        Twilio.init(accountSid, authToken)
        val smsMessage = Message.creator(
            PhoneNumber(numberTo),
            PhoneNumber(numberFrom),
            message
        ).create()
        println("SMS sent successfully with SID: ${smsMessage.sid}")
    }
}