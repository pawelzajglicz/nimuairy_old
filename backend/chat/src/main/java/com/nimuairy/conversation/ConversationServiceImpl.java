package com.nimuairy.conversation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimuairy.message.MessageService;
import com.nimuairy.auth.models.User;
import com.nimuairy.auth.security.services.UserService;
import com.nimuairy.brokers.MessageSender;
import com.nimuairy.message.Message;
import com.nimuairy.websocket.WebSocketPoolHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
@Slf4j
public class ConversationServiceImpl implements ConversationService {

	private final static int LAST_MESSAGES_NUMBER = 10;

	private final ConversationRepository conversationRepository;
	private final MessageSender messageSender;
	private final MessageService messageService;
	private final ObjectMapper objectMapper;
	private final UserService userService;
	private final WebSocketPoolHandler webSocketPoolHandler;

	@Override
	public LoadConversationDto loadConversationWithUser(Long interlocutorId) {

		Conversation conversation = conversationRepository.findConversationIdByInterlocutors(Set.of(interlocutorId, userService.currentUser().getId()));
		Page<Message> messagesPage = messageService.getLastMessages(conversation.getId(), LAST_MESSAGES_NUMBER);
		List<Message> messages = new ArrayList<>(messagesPage.getContent());
		Collections.reverse(messages);

		return new LoadConversationDto(conversation.getId(), conversation.getParticipants(), messages, messagesPage.getTotalElements());
	}

	@Override
	public Conversation getConversationById(Long conversationId) {
		return conversationRepository.findById(conversationId).orElseThrow(() -> new RuntimeException("No conversation with given ID!"));
	}

	@Override
	public void handleNewMessage(Message message) {
		messageService.save(message);
		messageSender.send(message);
	}

	@Override
	public void sendMessageToConversationParticipants(Message message) {
		Conversation conversation = getConversationById(message.getConversationId());

		for (User participant : conversation.getParticipants()) {

			try {
				TextMessage textMessage = new TextMessage(objectMapper.writeValueAsString(message));
				Set<WebSocketSession> sessions = webSocketPoolHandler.getSessionForUser(participant.getId());
				if (sessions != null && sessions.size() > 0) {
					for (WebSocketSession session : sessions) {
						session.sendMessage(textMessage);
					}
				}

			} catch (IOException e) {
				log.error("Occurred error at sending message with id {} to user with id {}", message.getId(), participant.getId());
				e.printStackTrace();
			}
		}
	}
}
