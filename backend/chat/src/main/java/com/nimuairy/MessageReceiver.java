package com.nimuairy;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MessageReceiver {

	@Autowired
	MessageService messageService;

	@Autowired
	MessageHandler messageHandler;

	@KafkaListener(topics = "SEND_MESSAGE")
	public void messagesSendToUser(@Payload String message, @Headers MessageHeaders headers) {

		JSONObject jsonObject = new JSONObject(message);

		if (WebSocketPool.websockets.get(jsonObject.getString("sendTo")) != null) {

			String accessToken = jsonObject.getString("accessToken");
			Long sendTo = Long.parseLong(jsonObject.getString("sendTo"));
			String msg = jsonObject.getString("msg");

			messageService.sendMessage(accessToken, sendTo, msg);

		}
	}
}
