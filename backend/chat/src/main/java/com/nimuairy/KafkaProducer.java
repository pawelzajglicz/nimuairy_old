package com.nimuairy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class KafkaProducer {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public void sendMessage(String topic, String message) {
		System.out.println(String.format("Message is being sent to topic %s", topic));
		kafkaTemplate.send(topic, message);
	}

}
