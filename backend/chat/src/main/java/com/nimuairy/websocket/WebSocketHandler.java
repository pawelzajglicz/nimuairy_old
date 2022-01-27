package com.nimuairy.websocket;

/*
import com.endpoint.SpringKafkaMessaging.cache.respository.CacheRepository;
import com.endpoint.SpringKafkaMessaging.message.broker.MessageSender;
import com.endpoint.SpringKafkaMessaging.persistent.model.User;
import com.endpoint.SpringKafkaMessaging.persistent.repository.UserRepository;
import org.json.JSONObject;*/
import com.nimuairy.MessageHandler;
import com.nimuairy.MessageSender;
import com.nimuairy.auth.models.User;
import com.nimuairy.auth.security.services.UserService;
import com.nimuairy.cache.CacheRepository;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.io.IOException;

@Component
@AllArgsConstructor
public class WebSocketHandler extends AbstractWebSocketHandler {

	final static String TICKET = "ticket";
	final static String MESSAGES_TOPIC = "messages";
	CacheRepository cacheRepository;
	MessageHandler messageHandler;
	MessageSender messageSender;
	UserService userService;

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {

		String parameters[] = session.getUri().getQuery().split("=");

		if (parameters.length == 2 && parameters[0].equals(TICKET)) {
			String ticket = parameters[1];
			Long senderId = cacheRepository.getUserIdByAccessToken(ticket).orElseThrow(() -> new RuntimeException("User not found"));
			messageHandler.removeFromSessionToPool(senderId, session);
		}
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {

		String parameters[] = session.getUri().getQuery().split("=");

		if (parameters.length == 2 && parameters[0].equals(TICKET)) {
			String ticket = parameters[1];
			Long senderId = cacheRepository.getUserIdByAccessToken(ticket).orElseThrow(() -> new RuntimeException("User not found"));
			messageHandler.addSessionToPool(senderId, session);
		}
		else {
			session.close();
		}
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage textMessage) throws Exception {

		System.out.println("textMessage.getPayload()" + textMessage.getPayload());

		JSONObject jsonObject = new JSONObject(textMessage.getPayload());
		//String topic = jsonObject.getString("topic");

		// only SEND_MESSAGE topic is available
	/*	if (topic == null && !topic.equals(MESSAGES_TOPIC)) {
			return;
		}*/

		messageSender.send("messages", textMessage.getPayload());
	}
	@Override
	protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws IOException {
		System.out.println("New Binary Message Received");
		session.sendMessage(message);
	}
}
