package com.nimuairy.brokers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimuairy.conversation.ConversationServiceImpl;
import com.nimuairy.message.Message;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@ConditionalOnProperty(value = "chat.broker",
					   havingValue = "rabbit",
					   matchIfMissing = true)
@Service
@Slf4j
public class MessageReceiverRabbitMq implements MessageReceiver {

	ConversationServiceImpl conversationService;
	ObjectMapper objectMapper;

	@RabbitListener(queues = "messages")
	public void listenToMessages(Message message) {
		conversationService.sendMessageToConversationParticipants(message);
	}
}
