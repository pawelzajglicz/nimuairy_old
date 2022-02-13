package com.nimuairy.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimuairy.conversation.ConversationServiceImpl;
import com.nimuairy.message.Message;
import com.nimuairy.ticket.TicketService;
import lombok.AllArgsConstructor;
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
	ConversationServiceImpl conversationService;
	ObjectMapper objectMapper;
	TicketService ticketService;
	WebSocketPoolHandler webSocketPoolHandler;

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {

		String[] parameters = session.getUri().getQuery().split("=");

		if (parameters.length == 2 && parameters[0].equals(TICKET)) {
			String ticket = parameters[1];
			Long senderId = ticketService.getUserIdByAccessToken(ticket).orElseThrow(() -> new RuntimeException("User not found"));
			webSocketPoolHandler.removeFromSessionToPool(senderId, session);
		}
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		String[] parameters = session.getUri().getQuery().split("=");

		if (parameters.length == 2 && parameters[0].equals(TICKET)) {
			String ticket = parameters[1];
			Long senderId = ticketService.getUserIdByAccessToken(ticket).orElseThrow(() -> new RuntimeException("User not found"));
			webSocketPoolHandler.addSessionToPool(senderId, session);
		} else {
			session.close();
		}
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage textMessage) throws Exception {
		Message message = objectMapper.readValue(textMessage.getPayload(), Message.class);
		conversationService.handleNewMessage(message);
	}

	@Override
	protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws IOException {
		session.sendMessage(message);
	}
}
