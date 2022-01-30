package com.nimuairy;

import com.nimuairy.auth.security.services.UserService;
import com.nimuairy.dtos.LoadConversationDto;
import com.nimuairy.models.Conversation;
import com.nimuairy.models.Message;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class ConversationServiceImpl {

	private ConversationRepository conversationRepository;
	private MessageService messageService;
	private UserService userService;

	private final static int LAST_MESSAGES_NUMBER = 10;

	public LoadConversationDto loadConversationWithUser(Long interlocutorId) {

		Conversation conversation = conversationRepository.findConversationIdByInterlocutors(Set.of(interlocutorId, userService.currentUser().getId()));
		Page<Message> messages = messageService.getLastMessages(conversation.getId(), LAST_MESSAGES_NUMBER);

		return new LoadConversationDto(conversation.getId(), conversation.getParticipants(), messages.getContent(), messages.getTotalElements());
	}
}
