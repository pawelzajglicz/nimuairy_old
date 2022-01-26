package com.nimuairy.websocket;
import com.nimuairy.MessageHandler;
import com.nimuairy.cache.CacheRepository;
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


	CacheRepository cacheRepository;

	MessageHandler messageHandler;

	@Bean
	public WebSocketHandler myMessageHandler() {
		return new WebSocketHandler(cacheRepository, messageHandler);
	}

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(myMessageHandler(), "/api/messaging").setAllowedOrigins("*");
	}

}
