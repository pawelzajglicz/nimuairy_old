package com.nimuairy.brokers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimuairy.message.Message;
import io.netty.handler.codec.MessageAggregationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@ConditionalOnProperty(value = "chat.broker",
					   havingValue = "kafka")
@Service
public class MessageSenderKafka implements MessageSender {

	@Value("${chat.kafka-topic}")
	private String topicName;

	private final KafkaTemplate<String, String> kafkaTemplate;
	private final ObjectMapper objectMapper;

	public MessageSenderKafka(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
		this.kafkaTemplate = kafkaTemplate;
		this.objectMapper = objectMapper;
	}

	public void send(String topic, String message) {
		kafkaTemplate.send(topic, message);
	}

	@Override
	public void send(Message message) {
		try {
			send(topicName, objectMapper.writeValueAsString(message));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new MessageAggregationException();
		}
	}
}

