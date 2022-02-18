package com.nimuairy.brokers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimuairy.conversation.ConversationServiceImpl;
import com.nimuairy.message.Message;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@ConditionalOnProperty(value = "chat.broker",
					   havingValue = "kafka")
@Service
@Slf4j
public class MessageReceiverKafka implements MessageReceiver {

	ConversationServiceImpl conversationService;
	ObjectMapper objectMapper;

	@KafkaListener(topics = "messages")
	public void listenToMessages(@Payload String messageAsJSON) throws JsonProcessingException {

		Message message = objectMapper.readValue(messageAsJSON, Message.class);
		conversationService.sendMessageToConversationParticipants(message);
	}
}
