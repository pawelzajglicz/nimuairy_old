package com.nimuairy.brokers;

import com.nimuairy.message.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@ConditionalOnProperty(value = "chat.broker",
		 			   havingValue = "rabbit",
					   matchIfMissing = true)
@Service
public class MessageSenderRabbitMq implements MessageSender {

	@Value("${chat.rabbitmq-queue}")
	private String queueName;

	private final RabbitTemplate rabbitTemplate;

	public MessageSenderRabbitMq(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	@Override
	public void send(Message message) {
		rabbitTemplate.convertAndSend(queueName, message);
	}
}

