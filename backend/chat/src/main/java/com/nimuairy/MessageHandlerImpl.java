package com.nimuairy;

import com.nimuairy.websocket.WebSocketPool;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Service
public class MessageHandlerImpl implements MessageHandler {

	@Override
	public void addSessionToPool(Long userId, WebSocketSession session) {

		Set<WebSocketSession> userSessions = WebSocketPool.websockets.get(userId);

		if (userSessions != null) {
			userSessions.add(session);
			WebSocketPool.websockets.put(userId, userSessions);
		} else {
			Set<WebSocketSession> newUserSessions = new HashSet<>();
			newUserSessions.add(session);
			WebSocketPool.websockets.put(userId, newUserSessions);
		}

	}

	@Override
	public void sendMessageToUser(Long userId, String message) throws IOException {

		Set<WebSocketSession> userSessions = WebSocketPool.websockets.get(userId);

		if (userSessions == null) {
			System.out.println("null sessions");
			return;
		}

		System.out.println("ppp" + userSessions.size());
		TextMessage textMessage = new TextMessage(message);
		for (WebSocketSession session : userSessions) {
			System.out.println("ppp");
			session.sendMessage(textMessage);
		}

	}

	@Override
	public void removeFromSessionToPool(Long userId, WebSocketSession session) {
		Set<WebSocketSession> userSessions = WebSocketPool.websockets.get(userId);

		if (userSessions != null) {
			for (WebSocketSession sessionItem : userSessions) {
				if (sessionItem.equals(session)) {
					userSessions.remove(session);
				}
			}
		}
		WebSocketPool.websockets.put(userId, userSessions);
	}
}
