package com.nimuairy.websocket;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class WebSocketPoolHandlerImpl implements WebSocketPoolHandler {
	public static Map<Long, Set<WebSocketSession>> websockets = new HashMap<>();

	@Override
	public void addSessionToPool(Long userId, WebSocketSession session) {

		Set<WebSocketSession> userSessions = WebSocketPoolHandlerImpl.websockets.get(userId);

		if (userSessions != null) {
			userSessions.add(session);
			WebSocketPoolHandlerImpl.websockets.put(userId, userSessions);
		} else {
			Set<WebSocketSession> newUserSessions = new HashSet<>();
			newUserSessions.add(session);
			WebSocketPoolHandlerImpl.websockets.put(userId, newUserSessions);
		}
	}

	@Override
	public void removeFromSessionToPool(Long userId, WebSocketSession session) {
		Set<WebSocketSession> userSessions = WebSocketPoolHandlerImpl.websockets.get(userId);

		if (userSessions != null) {
			userSessions.remove(session);
		}
		WebSocketPoolHandlerImpl.websockets.put(userId, userSessions);
	}

	@Override
	public Set<WebSocketSession> getSessionForUser(Long userId) {
		return websockets.get(userId);
	}
}
