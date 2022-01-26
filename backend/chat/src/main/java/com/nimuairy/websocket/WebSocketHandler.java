package com.nimuairy.websocket;

/*
import com.endpoint.SpringKafkaMessaging.cache.respository.CacheRepository;
import com.endpoint.SpringKafkaMessaging.message.broker.MessageSender;
import com.endpoint.SpringKafkaMessaging.persistent.model.User;
import com.endpoint.SpringKafkaMessaging.persistent.repository.UserRepository;
import org.json.JSONObject;*/
import com.nimuairy.MessageHandler;
import com.nimuairy.cache.CacheRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.OptionalLong;

@Component
@AllArgsConstructor
public class WebSocketHandler extends AbstractWebSocketHandler {


	final static String TICKET = "ticket";
	CacheRepository cacheRepository;
/*
	@Autowired
	UserRepository userRepository;*/

	MessageHandler messageHandler;


	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
/*
		String parameters[] = session.getUri().getQuery().split("=");

		if(parameters.length == 2 && parameters[0].equals("accessToken")) {
			String accessToken = parameters[1];

			Long senderUserId = 0L;
			String senderId = cacheRepository.getUserIdByAccessToken(accessToken);

			if(senderId == null) {
				User sender = userRepository.findByToken(accessToken);
				if(sender != null) {
					senderUserId = sender.getUserId();
				}
			} else {
				senderUserId = Long.valueOf(senderId);
			}
			if (senderUserId == 0L) {
				return;
			}

			messageHandler.removeFromSessionToPool(senderUserId, session);
		}
*/
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {

		messageHandler.addSessionToPool(1l, session);
		var uri = session.getUri();

		String parameters[] = session.getUri().getQuery().split("=");

		if (parameters.length == 2 && parameters[0].equals(TICKET)) {
			String ticket = parameters[1];

			Long senderUserId = 0L;
			Long senderId = cacheRepository.getUserIdByAccessToken(ticket).orElseThrow(() -> new RuntimeException("User not found"));

	/*		if(senderId == null) {
				User sender = userRepository.findByToken(accessToken);
				if(sender != null) {
					senderUserId = sender.getUserId();
				}
			} else {
				senderUserId = Long.valueOf(senderId);
			}
			if (senderUserId == 0L) {
				return;
			}*/

			messageHandler.addSessionToPool(senderUserId, session);
		}
		else {
			session.close();
		}

	}
/*
	@Autowired
	private MessageSender sender;*/

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage textMessage) throws Exception {

		System.out.println("textMessage.getPayload()" + textMessage.getPayload());
		textMessage.getPayload();
		System.out.println("asddd");
		//session.sendMessage(new TextMessage("asd"));
		System.out.println("zxc");
		messageHandler.sendMessageToUser(1l, "{\"asdqwe\":\"ddsss\"}");
		System.out.println("dfg");
/*
		JSONObject jsonObject = new JSONObject(textMessage.getPayload());
		String topic = jsonObject.getString("topic");

		// only SEND_MESSAGE topic is available
		if(topic == null && !topic.equals("SEND_MESSAGE")) {
			return;
		}

		sender.send(topic, textMessage.getPayload());*/
	}
/*
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
		System.out.println("New Text Message Received");
		session.sendMessage(message);
	}
*/
	@Override
	protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws IOException {
		System.out.println("New Binary Message Received");
		session.sendMessage(message);
	}
}
