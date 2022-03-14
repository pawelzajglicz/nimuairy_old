package com.nimuairy.websocket;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimuairy.conversation.ConversationServiceImpl;
import com.nimuairy.ticket.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@AllArgsConstructor
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer  {

	ConversationServiceImpl conversationService;
	ObjectMapper objectMapper;
	TicketService ticketService;
	WebSocketPoolHandler webSocketPoolHandler;

	@Bean
	public com.nimuairy.websocket.WebSocketHandler myMessageHandler() {
		return new com.nimuairy.websocket.WebSocketHandler(conversationService, objectMapper, ticketService, webSocketPoolHandler);
	}

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(myMessageHandler(), "/messaging").setAllowedOrigins("*");
	}

}
