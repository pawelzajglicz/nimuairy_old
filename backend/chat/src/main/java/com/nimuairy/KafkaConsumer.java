package com.nimuairy;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
public class KafkaConsumer {

	@KafkaListener(topics = "myTestTopic")
	public void listenTopic(ConsumerRecord<String, String> kafkaMessage) {
		System.out.print(String.format("Received a message: %s", kafkaMessage.value()));
	}

}
