package com.wypl.notification.emitter.kafka

import com.fasterxml.jackson.databind.ObjectMapper
import com.wypl.notification.global.kafka.KafkaMessage
import com.wypl.notification.global.kafka.KafkaProducerMessage
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class NotificationKafkaProducer(
    val kafkaTemplate: KafkaTemplate<String, String>,
    val objectMapper: ObjectMapper
) {

    fun sendSuccessOfFailureMessage(
        topic: String,
        key: NotificationKafkaProducerMessageType,
        message: KafkaProducerMessage
    ): Any {
        val messageWithJson = objectMapper.writeValueAsString(message)
        kafkaTemplate.send(topic, key.toString(), messageWithJson)
        return messageWithJson
    }

    fun send(topic: String, key: String, message: KafkaMessage): Any {
        val messageWithJson = objectMapper.writeValueAsString(message)
        kafkaTemplate.send(topic, key, messageWithJson)
        return messageWithJson
    }

    fun send(topic: String, message: KafkaMessage): Any {
        val messageWithJson = objectMapper.writeValueAsString(message)
        kafkaTemplate.send(topic, messageWithJson)
        return messageWithJson
    }
}