package com.nimuairy.websocket;

import org.springframework.web.socket.WebSocketSession;

import java.util.Set;

public interface WebSocketPoolHandler {
	void addSessionToPool(Long userId, WebSocketSession session);
	void removeFromSessionToPool(Long userId, WebSocketSession session);
	Set<WebSocketSession> getSessionForUser(Long userId);
}
