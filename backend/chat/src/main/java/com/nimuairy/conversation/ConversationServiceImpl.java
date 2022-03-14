package com.nimuairy.conversation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimuairy.jwt.JWTStore;
import com.nimuairy.message.MessageService;
import com.nimuairy.brokers.MessageSender;
import com.nimuairy.message.Message;
import com.nimuairy.serviceclients.UsersServiceClient;
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
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Slf4j
public class ConversationServiceImpl implements ConversationService {

	private final static int LAST_MESSAGES_NUMBER = 10;

	private final ConversationRepository conversationRepository;
	private final JWTStore jwtStore;
	private final MessageSender messageSender;
	private final MessageService messageService;
	private final ObjectMapper objectMapper;
	private final UsersServiceClient usersServiceClient;
	private final WebSocketPoolHandler webSocketPoolHandler;

	@Override
	public LoadConversationDto loadConversationWithUser(Long interlocutorId) {

		Conversation conversation = conversationRepository.findConversationIdByInterlocutors(Set.of(interlocutorId, Long.valueOf(jwtStore.loadUserId())));
		Page<Message> messagesPage = messageService.getLastMessages(conversation.getId(), LAST_MESSAGES_NUMBER);
		List<Message> messages = new ArrayList<>(messagesPage.getContent());
		Collections.reverse(messages);
		Set<Long> participantsId = conversation.getParticipantsByIds()
				.stream()
				.map(conversationUsers -> conversationUsers.getUserId())
				.collect(Collectors.toSet());

		Set<ConversationParticipant> conversationParticipants = usersServiceClient.getUsersByIds(participantsId);

		return new LoadConversationDto(conversation.getId(), conversationParticipants, messages, messagesPage.getTotalElements());
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

		for (ConversationUsers conversationUsers : conversation.getParticipantsByIds()) {

			try {
				TextMessage textMessage = new TextMessage(objectMapper.writeValueAsString(message));
				Set<WebSocketSession> sessions = webSocketPoolHandler.getSessionForUser(conversationUsers.getUserId());
				if (sessions != null && sessions.size() > 0) {
					for (WebSocketSession session : sessions) {
						session.sendMessage(textMessage);
					}
				}

			} catch (IOException e) {
				log.error("Occurred error at sending message with id {} to user with id {}", message.getId(), conversationUsers.getUserId());
				e.printStackTrace();
			}
		}
	}
}
